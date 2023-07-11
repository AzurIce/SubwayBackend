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

    @PostMapping("/login")
    public Result userLogin(@RequestBody @Valid LoginDto LoginDto){
        return userService.login(LoginDto);
    }

    //注册用户
    @PostMapping("/register")
    public Result userRegister(@RequestBody @Valid RegisterDto registerDto){
        return userService.register(registerDto);
    }

    //邮箱验证
    @GetMapping("/email")
    public Result sendEmailCode(@RequestParam
                                    @Valid @NotBlank(message = "邮箱不能为空")
                                    @Email(message = "邮箱格式不正确")
                                            String email){
        return Result.success(userEmailService.sendEmailCode(email));
    }

    //查询所有用户信息!!
    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Result getAllUserInfo(){
        return Result.success(userService.getAllUserInfo());
    }

    //根据id查询用户信息!!
    @GetMapping("/info")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Result getStuByID(@RequestParam @Valid
                                 @NotBlank(message = "用户id不能为空")
                                         String uid){
        return Result.success(userService.getUserByID(uid));
    }

    //删除用户信息!!
    @GetMapping("/delete")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Result deleteUserById(@RequestParam
                                     @Valid @NotBlank(message = "用户id不能为空")
                                             String uid){
        return Result.success(userService.deleteUserById(uid));
    }

    //更改用户名!!
    @GetMapping("/update/name")
    @PreAuthorize("hasAnyAuthority('ROLE_NORMAL','ROLE_COMPANY','ROLE_ADMIN')")
    public Result updateUserName(@RequestParam
                                     @Valid @NotBlank(message = "用户id不能为空")
                                             String id,
                                 @RequestParam
                                     @Valid @NotBlank(message = "新用户名不能为空")
                                             String name){
        return Result.success(userService.updateUserName(id,name));
    }

    //更改用户密码!!
    @GetMapping("/update/password") //改变数据库数据就用post
    @PreAuthorize("hasAnyAuthority('ROLE_NORMAL','ROLE_COMPANY','ROLE_ADMIN')")
    public Result updateUserPwd(@RequestParam
                                    @Valid @NotBlank(message = "用户id不能为空")
                                            String id,
                                @RequestParam
                                    @Valid @NotBlank(message = "新密码不能为空")
                                            String pwd){
        return Result.success(userService.updateUserPwd(id,pwd));
    }

    //更改用户权限!!
    @GetMapping("/update/permission") //改变数据库数据就用post
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Result updateUserPer(@RequestParam
                                    @Valid @NotBlank(message = "用户id不能为空")
                                            String id,
                                @RequestParam
                                    @Valid @NotBlank(message = "新权限不能为空")
                                            String per){
        return Result.success(userService.updateUserPer(id,per));
    }

    //更改用户邮箱！！
    @GetMapping("/update/email") //改变数据库数据就用post
    @PreAuthorize("hasAnyAuthority('ROLE_NORMAL','ROLE_COMPANY','ROLE_ADMIN')")
    public Result updateUserEmail(@RequestParam
                                      @Valid @NotBlank(message = "用户id不能为空")
                                              String id,
                                  @RequestParam
                                      @Valid @NotBlank(message = "新邮箱不能为空")
                                      @Email(message = "邮箱格式不正确")
                                              String email){
        return Result.success(userService.updateUserEmail(id,email));
    }
}
