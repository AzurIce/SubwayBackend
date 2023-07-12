package com.mtx.metro.exception;

import com.mtx.metro.utils.Result;

import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.net.BindException;

import static com.mtx.metro.constants.CodeConstants.CODE_PARAMETER_ERROR;
import static com.mtx.metro.constants.CodeConstants.CODE_SYSTEM_ERROR;

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
        return Result.error(CODE_PARAMETER_ERROR,e.getMessage());
    }

    /**
     * 将请求体解析并绑定到 java bean 时，如果出错，则抛出 MethodArgumentNotValidException 异常

     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Result handle(MethodArgumentNotValidException e){
        return Result.error(CODE_PARAMETER_ERROR,e.getBindingResult().getFieldError().getDefaultMessage());
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
        return Result.error(CODE_PARAMETER_ERROR, e.getMessage());
    }

    /**
     * Redis未连接报错
     * @param e
     * @return
     */
    @ExceptionHandler({RedisConnectionFailureException.class})
    @ResponseBody
    public Result handleConnectionException(RedisConnectionFailureException e){
        return Result.error(CODE_SYSTEM_ERROR,e.getMessage());
    }
}
