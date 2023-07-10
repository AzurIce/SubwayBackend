package com.mtx.metro.utils;

import com.mtx.metro.constants.CodeConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 响应类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result implements Serializable {

    private String code;//成功还是失败的约定码
    private String msg;//请求失败的原因
    private Object data;//后台所需要携带的数据

    //返回一个成功信息
    public static Result success(){
        return new Result(CodeConstants.CODE_200000,"操作成功",null);
    }

    //返回一个成功信息
    public static Result success(Object data){
        return new Result(CodeConstants.CODE_200000,"操作成功",data);
    }

    //系统错误
    public static Result error(){
        return new Result(CodeConstants.CODE_500000,"系统错误",null);
    }

    //返回一个失败信息
    public static Result error(String code,String msg){
        return new Result(code,msg,null);
    }


}

