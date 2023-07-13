package com.mtx.metro.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtx.metro.controller.dto.RegisterDto;
import com.mtx.metro.controller.dto.UpdateDto;
import com.mtx.metro.domain.Result;
import com.mtx.metro.exception.ServiceException;
import com.mtx.metro.utils.RedisCache;
import com.mtx.metro.controller.dto.LoginDto;
import com.mtx.metro.utils.LoginUser;
import com.mtx.metro.domain.User;
import com.mtx.metro.mapper.UserMapper;
import com.mtx.metro.service.UserService;
import com.mtx.metro.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
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
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "系统错误");
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
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR.value(),"系统错误");
        }return one;
    }


    @Override
    @Transactional
    public HashMap<String,String> login(LoginDto loginDto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUname(),loginDto.getPwd());

        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        if(Objects.isNull(authenticate))
            throw new ServiceException(HttpStatus.BAD_REQUEST.value(), "用户名或密码错误");

        //使用userid生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);

        //authenticate存入redis
        redisCache.setCacheObject("login:"+userId,loginUser);

        //把token响应给前端
        HashMap<String,String> map = new HashMap<>();
        map.put("token",jwt);
        map.put("permission",loginUser.getUser().getPermission());
        return map;
    }

    @Override
    @Transactional
    public User register(RegisterDto rdto) {
        User one = getUserInfoByName(rdto.getUname());
        if(one != null)
            throw new ServiceException(HttpStatus.FORBIDDEN.value(), "用户名已存在");

        if(userEmailService.checkEmailCode(rdto.getEmail(),rdto.getToken(),rdto.getCode())){
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            User user = new User();
            user.setName(rdto.getUname());
            user.setPassword(passwordEncoder.encode(rdto.getPwd()));
            user.setMail(rdto.getEmail());
            save(user);
        }
        return getUserInfoByName(rdto.getUname());
    }

    @Override
    @Transactional
    public List<User> getAllUserInfo() {
        List<User> list = userMapper.selectAllUserInfo();
        if(list.isEmpty())
            throw new ServiceException(HttpStatus.NOT_FOUND.value(), "记录不存在");

        return list;
    }

    @Override
    @Transactional
    public User getUserByID(String uid) {
        User one = getUserInfoByID(uid);
        if(one == null)
            throw new ServiceException(HttpStatus.NOT_FOUND.value(),"用户不存在");

        return userMapper.selectUserByID(uid);
    }

    @Override
    @Transactional
    public boolean deleteUserById(String uid) {
        User one = getUserInfoByID(uid);
        if(one == null) throw new ServiceException(HttpStatus.NOT_FOUND.value(),"用户不存在");

        if(one.getName().equals("admin"))
            throw new ServiceException(HttpStatus.FORBIDDEN.value(),"超级管理员admin不允许删除");

        if(one.getPermission().equals("3"))
            throw new ServiceException(HttpStatus.FORBIDDEN.value(), "管理员不可删除,如需删除请修改权限后再删除");

        if(removeById(uid)) return true;
        else throw new ServiceException(HttpStatus.NOT_FOUND.value(),"用户不存在");
    }

    @Override
    @Transactional
    public boolean updateUserInfo(UpdateDto ud) {
        String id = ud.getId();
        User user = getUserInfoByID(id);
        if(user.getName().equals("admin"))
            throw new ServiceException(HttpStatus.FORBIDDEN.value(),"超级管理员admin不可更新");

        boolean name = false, per = false, email = false;

        if(!user.getName().equals(ud.getName()))
            name = updateUserName(id,ud.getName());
        if(!user.getPermission().equals(ud.getPer()))
            per = updateUserPer(id, ud.getPer());
        if(!user.getMail().equals(ud.getEmail()))
            email = updateUserEmail(id,ud.getEmail());
        if(name || per || email) return true;
        else throw new ServiceException(HttpStatus.FORBIDDEN.value(),"用户信息没有改变");
    }

    @Override
    @Transactional
    public boolean updateUserName(String id,String newname) {
        User one = getUserInfoByName(newname);
        if(one != null)
            throw new ServiceException(HttpStatus.BAD_REQUEST.value(),"该用户名已存在");

        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(User::getId,id).set(User::getName,newname);
        int flag = userMapper.update(null,wrapper);
        if(flag >= 1) return true;
        else throw new ServiceException(HttpStatus.NOT_FOUND.value(), "用户不存在");
    }

    @Override
    @Transactional
    public boolean updateUserPwd(String id,String oldpwd, String newpwd) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        User user = getUserInfoByID(id);
        if(!passwordEncoder.matches(oldpwd,user.getPassword()))
            throw new ServiceException(HttpStatus.FORBIDDEN.value(),"原密码输入错误");
        if(newpwd.equals(oldpwd))
            throw new ServiceException(HttpStatus.FORBIDDEN.value(),"新密码与原密码相同");

        String token = passwordEncoder.encode(newpwd);

        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(User::getId,id).set(User::getPassword,token);

        int flag = userMapper.update(null,wrapper);
        if(flag >= 1) return true;
        else throw new ServiceException(HttpStatus.NOT_FOUND.value(), "用户不存在");
    }

    @Override
    @Transactional
    public boolean updateUserPer(String id, String newper) {
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(User::getId,id).set(User::getPermission,newper);
        int flag = userMapper.update(null,wrapper);
        if(flag >= 1) return true;
        else throw new ServiceException(HttpStatus.NOT_FOUND.value(), "用户不存在");
    }

    @Override
    @Transactional
    public boolean updateUserEmail(String id,String newemail) {
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(User::getId,id).set(User::getMail,newemail);
        int flag = userMapper.update(null,wrapper);
        if(flag >= 1) return true;
        else throw new ServiceException(HttpStatus.NOT_FOUND.value(), "用户不存在");
    }

    @Override
    @Transactional
    public String logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userid = loginUser.getUser().getId();
        redisCache.deleteObject("login:" + userid);
        return "退出成功";
    }
}

