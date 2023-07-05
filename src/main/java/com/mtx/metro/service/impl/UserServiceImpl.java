package com.mtx.metro.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtx.metro.common.CodeConstants;
import com.mtx.metro.controller.dto.LoginDto;
import com.mtx.metro.controller.dto.UserInfoDto;
import com.mtx.metro.mapper.UserMapper;
import com.mtx.metro.entity.User;
import com.mtx.metro.exception.ServiceException;
import com.mtx.metro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    UserMapper userMapper;

    public User getUserInfoByIdpwd(LoginDto loginDto){
        User one;
        try{
            one = userMapper.userLogin(loginDto.getUid(),loginDto.getPwd());
        }catch (Exception e){
            log.error(e.toString());
            throw new ServiceException(CodeConstants.CODE_500000,"系统错误");
        }return one;
    }

    @Override
    @Transactional
    public UserInfoDto userLogin(LoginDto loginDto) {
        User one = getUserInfoByIdpwd(loginDto);
        if(one != null) {
            UserInfoDto info = new UserInfoDto();
//            BeanUtil.copyProperties(one,info,true);//忽略大小写
//            System.out.println(one.getUid());
            info.setUid(one.getId());
            info.setUname(one.getName());
            info.setPer(one.getPermission());
            info.setMail(one.getMail());
            return info;
        } else throw new ServiceException(CodeConstants.CODE_600000,"用户名或密码错误");
    }
}
