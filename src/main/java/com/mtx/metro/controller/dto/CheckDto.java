package com.mtx.metro.controller.dto;

import lombok.Data;

@Data
public class CheckDto {
    private String email;
    private String code;
    private String token;
}
