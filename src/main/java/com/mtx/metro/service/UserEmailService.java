package com.mtx.metro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mtx.metro.domain.User;

public interface UserEmailService extends IService<User> {

    String sendEmailCode(String email);
}
