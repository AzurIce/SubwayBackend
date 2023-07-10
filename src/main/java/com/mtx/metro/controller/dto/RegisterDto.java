package com.mtx.metro.controller.dto;

import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class RegisterDto {
    private String uname;
    private String pwd;
    @Email(message = "邮箱格式不正确")
    private String email;
    private String token;
    private String code;
}
