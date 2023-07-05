package com.mtx.metro.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtx.metro.common.CodeConstants;
import com.mtx.metro.controller.dto.LoginDto;
import com.mtx.metro.dao.UserMapper;
import com.mtx.metro.entity.User;
import com.mtx.metro.exception.ServiceException;
import com.mtx.metro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.transform.Source;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    UserMapper userMapper;

    public User getUserInfoByIdpwd(LoginDto loginDto){
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(User::getUid,loginDto.getUid());
        wrapper.eq(User::getPwd,loginDto.getPwd());
        User one;
        try{
            one = getOne(wrapper,true);
//            System.out.println(getOne(wrapper).getId());
            return one;
        }catch (Exception e){
            log.error(e.toString());
            throw new ServiceException(CodeConstants.CODE_500000,"系统错误");
        }//return one;
    }

    @Override
    @Transactional
    public LoginDto userLogin(LoginDto loginDto) {
        User one = getUserInfoByIdpwd(loginDto);
        if(one != null) {
            LoginDto ld = new LoginDto();
//            BeanUtil.copyProperties(one,ld,true);//忽略大小写
            ld.setUid(one.getUid());
            ld.setPwd(one.getPwd());
            return ld;
        } else throw new ServiceException(CodeConstants.CODE_600000,"用户名或密码错误qaq");
    }
}
