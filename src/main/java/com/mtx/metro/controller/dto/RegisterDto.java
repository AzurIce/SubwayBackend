package com.mtx.metro.controller.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class RegisterDto {
    @NotBlank(message = "用户名不能为空")
    private String uname;

    @NotBlank(message = "密码不能为空")
    private String pwd;

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    @NotBlank(message = "token不能为空")
    private String token;

    @NotBlank(message = "验证码不能为空")
    private String code;
}
