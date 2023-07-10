package com.mtx.metro.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtx.metro.constants.CodeConstants;
import com.mtx.metro.controller.dto.RegisterDto;
import com.mtx.metro.service.UserEmailService;
import com.mtx.metro.utils.Result;
import com.mtx.metro.exception.ServiceException;
import com.mtx.metro.utils.RedisCache;
import com.mtx.metro.controller.dto.LoginDto;
import com.mtx.metro.utils.LoginUser;
import com.mtx.metro.domain.User;
import com.mtx.metro.mapper.UserMapper;
import com.mtx.metro.service.UserService;
import com.mtx.metro.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private UserEmailServiceImpl userEmailService;

    @Autowired
    private UserMapper userMapper;

    public User getUserInfoByName(String name){
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(User::getName,name);
        User one;
        try{
            one = getOne(wrapper);
        }catch (Exception e){
            log.error(e.toString());
            throw new ServiceException(CodeConstants.CODE_500000,"系统错误");
        }return one;
    }

    public User getUserInfoByID(String uid){
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(User::getId,uid);
        User one;
        try{
            one = getOne(wrapper);
        }catch (Exception e){
            log.error(e.toString());
            throw new ServiceException(CodeConstants.CODE_500000,"系统错误");
        }return one;
    }


    @Override
    @Transactional
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
    @Transactional
    public Result register(RegisterDto rdto) {
        User one = getUserInfoByName(rdto.getUname());

        if(one == null){
            if(userEmailService.checkEmailCode(rdto.getEmail(),rdto.getToken(),rdto.getCode())){
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                User user = new User();
                user.setName(rdto.getUname());
                user.setPassword(passwordEncoder.encode(rdto.getPwd()));
                user.setMail(rdto.getEmail());
                save(user);
            }
        }else throw new ServiceException(CodeConstants.CODE_600000,"用户名已存在");

        return Result.success(getUserInfoByName(rdto.getUname()));
    }

    @Override
    @Transactional
    public Page<User> getAllUserInfo(Page<User> page) {
        return userMapper.selectAllUserInfo(page);
    }

    @Override
    @Transactional
    public Page<User> getUserByID(Page<User> page, String uid) {
        User one = getUserInfoByID(uid);
        if(one != null){
            return userMapper.selectUserByID(page,uid);
        }else throw new ServiceException(CodeConstants.CODE_600000,"用户不存在");
    }

    @Override
    @Transactional
    public Result logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getUser().getId();
        redisCache.deleteObject("login:" + userid);
        return Result.success("退出成功");
    }
}
