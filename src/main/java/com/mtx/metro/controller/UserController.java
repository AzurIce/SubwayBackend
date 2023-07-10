package com.mtx.metro.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtx.metro.constants.CodeConstants;
import com.mtx.metro.controller.dto.RegisterDto;
import com.mtx.metro.domain.User;
import com.mtx.metro.service.impl.UserEmailServiceImpl;
import com.mtx.metro.utils.Result;
import com.mtx.metro.controller.dto.LoginDto;
import com.mtx.metro.exception.ServiceException;
import com.mtx.metro.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;
import java.util.List;

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

    //查询所有用户信息
    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Transactional(rollbackFor = Exception.class)
    public Result getAllUserInfo(){
        return Result.success(userService.getAllUserInfo(new Page<>(1,2)));
    }

    //根据id查询用户信息
    @GetMapping("/info")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Result getStuByID(@RequestParam String uid){
        if(StrUtil.isBlank(uid)) {
            return Result.error(CodeConstants.CODE_400000,"参数错误");
        }
        return Result.success(userService.getUserByID(new Page<>(1,5),uid));
    }

    //删除用户信息
    @GetMapping("/delete")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Result deleteUserById(@RequestParam String uid){
        if(StrUtil.isBlank(uid)) {
            return Result.error(CodeConstants.CODE_400000,"参数错误");
        }
        return Result.success(userService.deleteUserById(uid));
    }

    @GetMapping("/update") //改变数据库数据就用post
    @PreAuthorize("hasAnyAuthority('ROLE_NORMAL','ROLE_COMPANY','ROLE_ADMIN')")
    public Result updateUserName(@RequestParam String id,@RequestParam String name){
        if(StrUtil.isBlank(id) || StrUtil.isBlank(name)) {
            return Result.error(CodeConstants.CODE_400000,"参数错误");
        }
        return Result.success(userService.updateUserName(id,name));
    }
    @GetMapping("/update") //改变数据库数据就用post
    @PreAuthorize("hasAnyAuthority('ROLE_NORMAL','ROLE_COMPANY','ROLE_ADMIN')")
    public Result updateUserPwd(@RequestParam String id,@RequestParam String pwd){
        if(StrUtil.isBlank(id) || StrUtil.isBlank(pwd)) {
            return Result.error(CodeConstants.CODE_400000,"参数错误");
        }
        return Result.success(userService.updateUserPwd(id,pwd));
    }
    @GetMapping("/update") //改变数据库数据就用post
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Result updateUserPer(@RequestParam String id,@RequestParam String per){
        if(StrUtil.isBlank(id) || StrUtil.isBlank(per)) {
            return Result.error(CodeConstants.CODE_400000,"参数错误");
        }
        return Result.success(userService.updateUserPer(id,per));
    }
    @GetMapping("/update") //改变数据库数据就用post
    @PreAuthorize("hasAnyAuthority('ROLE_NORMAL','ROLE_COMPANY','ROLE_ADMIN')")
    public Result updateUserEmail(@RequestParam String id,
                                  @RequestParam
                                  @Email(message = "邮箱格式不正确") String email){
        if(StrUtil.isBlank(id) || StrUtil.isBlank(email)) {
            return Result.error(CodeConstants.CODE_400000,"参数错误");
        }
        return Result.success(userService.updateUserEmail(id,email));
    }
}
