package com.mtx.metro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mtx.metro.controller.dto.LoginDto;
import com.mtx.metro.domain.LoginUser;
import com.mtx.metro.domain.User;

public interface UserService extends IService<User> {

    LoginUser userLogin(LoginDto LoginDto);
}
