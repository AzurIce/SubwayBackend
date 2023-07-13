package com.mtx.metro.exception;

import com.mtx.metro.domain.Result;

import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.net.BindException;

import static com.mtx.metro.constants.CodeConstants.*;

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
    public ResponseEntity<Result> handle(ServiceException e){
        String code = e.getCode();
        if(code.equals(HttpStatus.OK.toString()))
            //200
            return new ResponseEntity<>(Result.error(e.getCode(),e.getMessage()),HttpStatus.OK);
        else if(code.equals(HttpStatus.BAD_REQUEST.toString()))
            //400
            return new ResponseEntity<>(Result.error(e.getCode(),e.getMessage()), HttpStatus.BAD_REQUEST);
        else if(code.equals(HttpStatus.UNAUTHORIZED.toString()))
            //401
            return new ResponseEntity<>(Result.error(e.getCode(),e.getMessage()),HttpStatus.UNAUTHORIZED);
        else if(code.equals(HttpStatus.FORBIDDEN.toString()))
            //403
            return new ResponseEntity<>(Result.error(e.getCode(),e.getMessage()), HttpStatus.FORBIDDEN);
        else if(code.equals(HttpStatus.NOT_FOUND.toString()))
            //404
            return new ResponseEntity<>(Result.error(e.getCode(),e.getMessage()), HttpStatus.NOT_FOUND);
        else if(code.equals(HttpStatus.INTERNAL_SERVER_ERROR.toString()))
            //500
            return new ResponseEntity<>(Result.error(e.getCode(),e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        else
            //501
            return new ResponseEntity<>(Result.error(e.getCode(),e.getMessage()), HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * 普通参数(非 java bean)校验出错时抛出 ConstraintViolationException 异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ResponseEntity<Result> handle(ConstraintViolationException e){
        return ResponseEntity.badRequest().body(Result.error(CODE_PARAMETER_ERROR,e.getMessage()));
    }

    /**
     * 将请求体解析并绑定到 java bean 时，如果出错，则抛出 MethodArgumentNotValidException 异常

     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<Result> handle(MethodArgumentNotValidException e){
        return ResponseEntity.badRequest().body(Result.error(CODE_PARAMETER_ERROR,e.getBindingResult().getFieldError().getDefaultMessage()));
    }

    /**
     * 表单绑定到 java bean 出错时抛出 BindException 异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler({BindException.class})
    @ResponseBody
    public ResponseEntity<Result> handleBindException(BindException e) {
        return ResponseEntity.badRequest().body(Result.error(CODE_PARAMETER_ERROR, e.getMessage()));
    }

    /**
     * Redis未连接报错
     * @param e
     * @return
     */
    @ExceptionHandler({RedisConnectionFailureException.class})
    @ResponseBody
    public ResponseEntity<Result> handleConnectionException(RedisConnectionFailureException e){
        //500
        return ResponseEntity.internalServerError().body(Result.error(CODE_SYSTEM_ERROR,e.getMessage()));
    }
}
