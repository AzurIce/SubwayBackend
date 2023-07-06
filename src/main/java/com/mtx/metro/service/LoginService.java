package com.mtx.metro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mtx.metro.domain.Result;
import com.mtx.metro.controller.dto.LoginDto;
import com.mtx.metro.domain.User;

public interface LoginService extends IService<User> {
    public Result login(LoginDto loginDto);

    public Result logout();
}
