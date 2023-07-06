package com.mtx.metro.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtx.metro.constants.CodeConstants;
import com.mtx.metro.controller.dto.CheckDto;
import com.mtx.metro.controller.dto.RegisterDto;
import com.mtx.metro.domain.Result;
import com.mtx.metro.exception.ServiceException;
import com.mtx.metro.utils.RedisCache;
import com.mtx.metro.controller.dto.LoginDto;
import com.mtx.metro.domain.LoginUser;
import com.mtx.metro.domain.User;
import com.mtx.metro.mapper.UserMapper;
import com.mtx.metro.service.LoginService;
import com.mtx.metro.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

@Service
public class LoginServiceImpl extends ServiceImpl<UserMapper, User> implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    public User getUserInfoByName(RegisterDto rdto){
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(User::getName,rdto.getUname());
        User one;
        try{
            one = getOne(wrapper);
        }catch (Exception e){
            log.error(e.toString());
            throw new ServiceException(CodeConstants.CODE_500000,"系统错误");
        }return one;
    }

    @Override
    public Result login(LoginDto loginDto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUname(),loginDto.getPwd());

        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        if(Objects.isNull(authenticate)){
            throw new ServiceException(CodeConstants.CODE_600000,"用户名或密码错误");
        }
        //使用userid生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);

        //authenticate存入redis
        redisCache.setCacheObject("login:"+userId,loginUser);

        //把token响应给前端
        HashMap<String,String> map = new HashMap<>();
        map.put("token",jwt);
        return Result.success(map);
    }

    @Override
    public Result register(RegisterDto rdto) {
        User one = getUserInfoByName(rdto);

        if(one == null){
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            User user = new User();
            user.setName(rdto.getUname());
            user.setPassword(passwordEncoder.encode(rdto.getPwd()));
            user.setMail(rdto.getEmail());
            save(user);
        }else throw new ServiceException(CodeConstants.CODE_600000,"用户名已存在");

        return Result.success(getUserInfoByName(rdto));
    }

    @Override
    public boolean checkEmailCode(CheckDto cdto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String email = cdto.getUmail();
        String code = cdto.getCode();
        String token = cdto.getToken();

        String mailCode = email + code;

        return passwordEncoder.matches(mailCode, token);
    }

    @Override
    public String sendEmailCode(String email) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(from);
        msg.setTo(email);
        msg.setSentDate(new Date());
        msg.setSubject("【地铁客流量预测系统】登录邮箱验证");
        String code = RandomUtil.randomNumbers(6);
        msg.setText("您本次登录的验证码为：" + code + "，有效期5分钟，请妥善保管，切勿泄露。");
        javaMailSender.send(msg);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String mailCode = email + code;
        return passwordEncoder.encode(mailCode);
    }

    @Override
    public Result logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getUser().getId();
        redisCache.deleteObject("login:" + userid);
        return Result.success("退出成功");
    }
}

