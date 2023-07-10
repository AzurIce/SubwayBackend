package com.mtx.metro.controller.dto;

import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class CheckDto {
    @Email(message = "邮箱格式不正确")
    private String email;
    private String code;
    private String token;
}
