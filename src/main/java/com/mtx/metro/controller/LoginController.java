package com.mtx.metro.controller;

import cn.hutool.core.util.StrUtil;
import com.mtx.metro.constants.CodeConstants;
import com.mtx.metro.controller.dto.CheckDto;
import com.mtx.metro.controller.dto.RegisterDto;
import com.mtx.metro.domain.Result;
import com.mtx.metro.controller.dto.LoginDto;
import com.mtx.metro.exception.ServiceException;
import com.mtx.metro.service.impl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private LoginServiceImpl loginService;

//    @EnableGlobalMethodSecurity(prePostEnabled = true)

    @PostMapping("/login")
    public Result userLogin(@RequestBody LoginDto LoginDto){
        String uname = LoginDto.getUname();
        String pwd = LoginDto.getPwd();
        if(StrUtil.isBlank(uname) || StrUtil.isBlank(pwd)) {
            throw new ServiceException(CodeConstants.CODE_400000,"参数错误");
        }
        return loginService.login(LoginDto);
    }

    //注册用户
    @PostMapping("/register") //改变数据库数据就用post
    public Result userRegister(@RequestBody RegisterDto registerDto){
        String uname = registerDto.getUname();
        String pwd = registerDto.getPwd();
        String email = registerDto.getEmail();
        String token = registerDto.getToken();
        String code = registerDto.getCode();

        if(StrUtil.isBlank(uname) || StrUtil.isBlank(pwd)
                || StrUtil.isBlank(email) || StrUtil.isBlank(token)
                || StrUtil.isBlank(code)) {
            throw new ServiceException(CodeConstants.CODE_400000,"参数错误");
        }
        return loginService.register(registerDto);
    }

    //邮箱验证
    @GetMapping("/email")
    public Result sendEmailCode(@RequestParam String email){
        if(StrUtil.isBlank(email)) {
            throw new ServiceException(CodeConstants.CODE_400000,"参数错误");
        }
        return Result.success(loginService.sendEmailCode(email));
    }
}
