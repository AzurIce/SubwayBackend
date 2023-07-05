package com.mtx.metro.controller;

import cn.hutool.core.util.StrUtil;
import com.mtx.metro.common.CodeConstants;
import com.mtx.metro.common.Result;
import com.mtx.metro.controller.dto.LoginDto;
import com.mtx.metro.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/login")
    public Result userLogin(@RequestBody LoginDto LoginDto){
        String uid = LoginDto.getUid();
        String pwd = LoginDto.getPwd();
        if(StrUtil.isBlank(uid) || StrUtil.isBlank(pwd)) {
            return Result.error(CodeConstants.CODE_400000,"参数错误");
        }
        return Result.success(userService.userLogin(LoginDto));
    }
}
