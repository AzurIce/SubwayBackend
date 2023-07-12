package com.mtx.metro.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mtx.metro.controller.dto.RegisterDto;
import com.mtx.metro.controller.dto.UpdateDto;
import com.mtx.metro.utils.Result;
import com.mtx.metro.controller.dto.LoginDto;
import com.mtx.metro.domain.User;

import java.util.List;

public interface UserService extends IService<User> {
    Result login(LoginDto loginDto);

    Result register(RegisterDto registerDto);

    List<User> getAllUserInfo();

    User getUserByID(Integer uid);

    boolean deleteUserById(Integer uid);

    boolean updateUserInfo(UpdateDto ud);

    boolean updateUserName(Integer id,String name);

    boolean updateUserPwd(Integer id,String oldpwd, String newpwd);

    boolean updateUserPer(Integer id,String per);

    boolean updateUserEmail(Integer id,String email);

    Result logout();
}
