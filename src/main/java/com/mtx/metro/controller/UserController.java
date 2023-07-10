package com.mtx.metro.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtx.metro.constants.CodeConstants;
import com.mtx.metro.controller.dto.RegisterDto;
import com.mtx.metro.service.impl.UserEmailServiceImpl;
import com.mtx.metro.utils.Result;
import com.mtx.metro.controller.dto.LoginDto;
import com.mtx.metro.exception.ServiceException;
import com.mtx.metro.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableTransactionManagement
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserEmailServiceImpl userEmailService;

    @PostMapping("/login")
    @Transactional(rollbackFor = Exception.class)
    public Result userLogin(@RequestBody LoginDto LoginDto){
        String uname = LoginDto.getUname();
        String pwd = LoginDto.getPwd();
        if(StrUtil.isBlank(uname) || StrUtil.isBlank(pwd)) {
            throw new ServiceException(CodeConstants.CODE_400000,"参数错误");
        }
        return userService.login(LoginDto);
    }

    //注册用户
    @PostMapping("/register") //改变数据库数据就用post
    @Transactional(rollbackFor = Exception.class)
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
        return userService.register(registerDto);
    }

    //邮箱验证
    @GetMapping("/email")
    @Transactional(rollbackFor = Exception.class)
    public Result sendEmailCode(@RequestParam String email){
        if(StrUtil.isBlank(email)) {
            throw new ServiceException(CodeConstants.CODE_400000,"参数错误");
        }
        return Result.success(userEmailService.sendEmailCode(email));
    }

    @PostMapping("/all")
    @Transactional(rollbackFor = Exception.class)
    public Result getAllUserInfo(){
        return Result.success(userService.getAllUserInfo(new Page<>(1,5)));
    }

    @GetMapping("/info") //不改变数据库数据就用get
    public Result getStuByID(@RequestParam String uid){
        if(StrUtil.isBlank(uid)) {
            return Result.error(CodeConstants.CODE_400000,"参数错误");
        }
        return Result.success(userService.getUserByID(new Page<>(1,5),uid));
    }
}
