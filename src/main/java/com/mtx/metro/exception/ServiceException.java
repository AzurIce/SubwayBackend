package com.mtx.metro.exception;

import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException{
    //状态码
    private String code;

    public ServiceException(String code, String msg){
        super(msg);
        this.code = code;
    }
}
