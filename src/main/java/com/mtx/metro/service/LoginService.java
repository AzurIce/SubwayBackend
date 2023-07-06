package com.mtx.metro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mtx.metro.controller.dto.CheckDto;
import com.mtx.metro.controller.dto.RegisterDto;
import com.mtx.metro.domain.Result;
import com.mtx.metro.controller.dto.LoginDto;
import com.mtx.metro.domain.User;

public interface LoginService extends IService<User> {
    Result login(LoginDto loginDto);

    Result register(RegisterDto registerDto);

    boolean checkEmailCode(CheckDto cdto);

    String sendEmailCode(String email);

    Result logout();
}
