package com.mtx.metro.exception;

import com.mtx.metro.constants.CodeConstants;
import com.mtx.metro.utils.Result;

import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.net.BindException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.lang.String;

//全局异常处理器
@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 如果抛出的是ServiceException,则调用该方法
     * @param e
     * @return
     */
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public Result handle(ServiceException e){
        return Result.error(e.getCode(),e.getMessage());
    }

    /**
     * 普通参数(非 java bean)校验出错时抛出 ConstraintViolationException 异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public Result handle(ConstraintViolationException e){
        return Result.error(CodeConstants.CODE_400000,e.getMessage());
    }

    /**
     * 将请求体解析并绑定到 java bean 时，如果出错，则抛出 MethodArgumentNotValidException 异常

     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Result handle(MethodArgumentNotValidException e){
        return Result.error(CodeConstants.CODE_400000,e.getBindingResult().getFieldError().getDefaultMessage());
    }

    /**
     * 表单绑定到 java bean 出错时抛出 BindException 异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler({BindException.class})
    @ResponseBody
    public Result handleBindException(BindException e) {
        return Result.error(CodeConstants.CODE_400000, e.getMessage());
    }
}
