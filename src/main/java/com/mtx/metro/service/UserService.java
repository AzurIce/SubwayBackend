package com.mtx.metro.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mtx.metro.controller.dto.RegisterDto;
import com.mtx.metro.utils.Result;
import com.mtx.metro.controller.dto.LoginDto;
import com.mtx.metro.domain.User;

import java.util.List;

public interface UserService extends IService<User> {
    Result login(LoginDto loginDto);

    Result register(RegisterDto registerDto);

    List<User> getAllUserInfo();

    User getUserByID(String uid);

    boolean deleteUserById(String uid);

    boolean updateUserName(String id,String name);

    boolean updateUserPwd(String id,String pwd);

    boolean updateUserPer(String id,String per);

    boolean updateUserEmail(String id,String email);

    Result logout();
}
