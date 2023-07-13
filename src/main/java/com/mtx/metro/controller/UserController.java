package com.mtx.metro.controller;

import com.mtx.metro.controller.dto.RegisterDto;
import com.mtx.metro.controller.dto.UpdateDto;
import com.mtx.metro.service.impl.UserEmailServiceImpl;
import com.mtx.metro.domain.Result;
import com.mtx.metro.controller.dto.LoginDto;
import com.mtx.metro.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@RestController
@Validated
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserEmailServiceImpl userEmailService;

    //登录用户
    @PostMapping("/login")
    public ResponseEntity<Result> login(@RequestBody @Valid LoginDto LoginDto){
        return new ResponseEntity<>(Result.success(userService.login(LoginDto)), HttpStatus.OK);
    }

    //登出用户
    @PostMapping("/logout")
    public ResponseEntity<Result> logout(){
        return new ResponseEntity<>(Result.success(userService.logout()), HttpStatus.OK);
    }

    //注册用户
    @PostMapping("/register")
    public ResponseEntity<Result> userRegister(@RequestBody @Valid RegisterDto registerDto){
        return new ResponseEntity<>(Result.success(userService.register(registerDto)), HttpStatus.OK);
    }

    //邮箱验证
    //TODO:阻塞验证码

    @GetMapping("/email")
    public ResponseEntity<Result> sendEmailCode(@RequestParam
                                    @Valid @NotBlank(message = "邮箱不能为空")
                                    @Email(message = "邮箱格式不正确")
                                            String email){
        return new ResponseEntity<>(Result.success(userEmailService.sendEmailCode(email)), HttpStatus.OK);
    }

    //查询所有用户信息!!
    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Result> getAllUserInfo(){
        return new ResponseEntity<>(Result.success(userService.getAllUserInfo()), HttpStatus.OK);
    }

    //根据id查询用户信息!!
    @GetMapping("/info")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Result> getStuByID(@RequestParam
                                         String uid){
        return new ResponseEntity<>(Result.success(userService.getUserByID(uid)), HttpStatus.OK);
    }

    //删除用户信息!!
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Result> deleteUserById(@RequestParam String uid){
        return new ResponseEntity<>(Result.success(userService.deleteUserById(uid)), HttpStatus.OK);
    }

    //更新用户数据
    @PostMapping("/update")
    @PreAuthorize("hasAnyAuthority('ROLE_NORMAL','ROLE_COMPANY','ROLE_ADMIN')")
    public ResponseEntity<Result> updateUserInfo(@RequestBody
                                 @Valid UpdateDto ud){
        return new ResponseEntity<>(Result.success(userService.updateUserInfo(ud)), HttpStatus.OK);
    }

    //更改用户密码!!
    @PostMapping("/update/password") //改变数据库数据就用post
    @PreAuthorize("hasAnyAuthority('ROLE_NORMAL','ROLE_COMPANY','ROLE_ADMIN')")
    public ResponseEntity<Result> updateUserPwd(@RequestParam
                                            String id,
                                @RequestParam
                                @Valid @NotBlank(message = "原密码不能为空")
                                        String oldpwd,
                                @RequestParam
                                    @Valid @NotBlank(message = "新密码不能为空")
                                            String newpwd){
        return new ResponseEntity<>(Result.success(userService.updateUserPwd(id,oldpwd,newpwd)), HttpStatus.OK);
    }
}
