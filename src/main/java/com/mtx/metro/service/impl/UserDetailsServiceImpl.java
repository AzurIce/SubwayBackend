package com.mtx.metro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtx.metro.constants.CodeConstants;
import com.mtx.metro.controller.dto.LoginDto;
import com.mtx.metro.domain.LoginUser;
import com.mtx.metro.exception.ServiceException;
import com.mtx.metro.mapper.UserMapper;
import com.mtx.metro.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserDetailsServiceImpl extends ServiceImpl<UserMapper, User> implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

//    public User getUserInfoByNamePwd(LoginDto loginDto){
//        User one;
//        try{
//            one = userMapper.userLogin(loginDto.getUname(),loginDto.getPwd());
//        }catch (Exception e){
//            log.error(e.toString());
//            throw new ServiceException(CodeConstants.CODE_500000,"系统错误");
//        }return one;
//    }

//    @Override
//    @Transactional
//    public UserInfoDto userLogin(LoginDto loginDto) {
//        User one = getUserInfoByNamePwd(loginDto);
//        if(one != null) {
//            UserInfoDto info = new UserInfoDto();
//            info.setUid(one.getId());
//            info.setUname(one.getName());
//            info.setPer(one.getPermission());
//            info.setMail(one.getMail());
//            return info;
//        } else throw new ServiceException(CodeConstants.CODE_600000,"用户名或密码错误");
//    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        //根据用户名查询用户信息
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getName,username);
        User user = userMapper.selectOne(wrapper);
        //如果查询不到数据就通过抛出异常来给出提示
        if(Objects.isNull(user)){
            throw new ServiceException(CodeConstants.CODE_600000,"用户名或密码错误");
        }
        //TODO 根据用户查询权限信息 添加到LoginUser中
        List<String> permissionKeyList = Collections.singletonList(userMapper.selectPermsByUserId(user.getId()).toString());

        //封装成UserDetails对象返回
        return new LoginUser(user,permissionKeyList);
    }
}
