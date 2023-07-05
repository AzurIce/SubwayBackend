package com.mtx.metro.controller.dto;

import com.mtx.metro.enums.PerEnum;
import lombok.Data;

import java.security.Permission;

@Data
public class UserInfoDto {
    private String uid;
    private String uname;
    private PerEnum per;
    private String mail;
}
