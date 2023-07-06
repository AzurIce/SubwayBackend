package com.mtx.metro.controller;

import cn.hutool.core.util.StrUtil;
import com.mtx.metro.constants.CodeConstants;
import com.mtx.metro.domain.Result;
import com.mtx.metro.controller.dto.LoginDto;
import com.mtx.metro.service.impl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class LoginController {

//    @Autowired
//    private UserDetailsServiceImpl userService;

    @Autowired
    private LoginServiceImpl loginService;

//    @EnableGlobalMethodSecurity(prePostEnabled = true)

    @PostMapping("/login")
    @PreAuthorize("hasAuthority('test')")
    public Result userLogin(@RequestBody LoginDto LoginDto){
        String uname = LoginDto.getUname();
        String pwd = LoginDto.getPwd();
        if(StrUtil.isBlank(uname) || StrUtil.isBlank(pwd)) {
            return Result.error(CodeConstants.CODE_400000,"参数错误");
        }
//        return Result.success(userService.userLogin(LoginDto));
        return Result.success(loginService.login(LoginDto));
    }
}
