package com.mtx.metro.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * 响应类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result implements Serializable {

    private int code;//成功还是失败的约定码
    private String msg;//请求失败的原因
    private Object data;//后台所需要携带的数据

    //返回一个成功信息
    public static Result success(){
        return new Result(HttpStatus.OK.value(), "操作成功",null);
    }

    //返回一个成功信息
    public static Result success(Object data){
        return new Result(HttpStatus.OK.value(),"操作成功",data);
    }

    //返回一个失败信息
    public static Result error(int code,String msg){
        return new Result(code,msg,null);
    }

}

