package com.mtx.metro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mtx.metro.controller.dto.RegisterDto;
import com.mtx.metro.controller.dto.UpdateDto;
import com.mtx.metro.domain.Result;
import com.mtx.metro.controller.dto.LoginDto;
import com.mtx.metro.domain.User;

import java.util.HashMap;
import java.util.List;

public interface UserService extends IService<User> {
    HashMap<String,String> login(LoginDto loginDto);

    User register(RegisterDto registerDto);

    List<User> getAllUserInfo();

    User getUserByID(String uid);

    boolean deleteUserById(String uid);

    boolean updateUserInfo(UpdateDto ud);

    boolean updateUserName(String id,String name);

    boolean updateUserPwd(String id,String oldpwd, String newpwd);

    boolean updateUserPer(String id,String per);

    boolean updateUserEmail(String id,String email);

    String logout();
}
