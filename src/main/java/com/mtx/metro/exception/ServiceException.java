package com.mtx.metro.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(code = HttpStatus.PAYMENT_REQUIRED,reason = "this is ServiceException")
public class ServiceException extends RuntimeException{
    //状态码
    private int code;

    public ServiceException(int code, String msg){
        super(msg);
        this.code = code;
    }
}
