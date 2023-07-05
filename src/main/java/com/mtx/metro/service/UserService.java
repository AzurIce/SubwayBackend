package com.mtx.metro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mtx.metro.controller.dto.LoginDto;
import com.mtx.metro.controller.dto.UserInfoDto;
import com.mtx.metro.entity.User;

public interface UserService extends IService<User> {

    UserInfoDto userLogin(LoginDto LoginDto);
}
