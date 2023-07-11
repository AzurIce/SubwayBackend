package com.mtx.metro.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginDto {
    @NotBlank(message = "用户名不能为空")
    private String uname;

    @NotBlank(message = "密码不能为空")
    private String pwd;
}
