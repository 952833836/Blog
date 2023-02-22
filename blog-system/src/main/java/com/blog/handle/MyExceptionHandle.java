package com.blog.handle;

import com.blog.enums.HttpResponseCode;
import com.blog.pojo.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 与controller相关的
 * 全局异常统一处理
 *
 * @author a1387
 * @date 2023/02/21
 */
@RestControllerAdvice
@Slf4j
public class MyExceptionHandle {

    /**
     * 运行时异常处理程序
     *
     * @param e e
     * @return {@link ResponseResult}
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseResult runtimeExceptionHandler(RuntimeException e) {
        log.error(e.getMessage());
        return ResponseResult.errorResult(HttpResponseCode.SYSTEM_ERROR, e.getMessage());
    }

    /**
     * hibernate validator 数据绑定验证异常拦截
     * get请求进行jsr303校验时，返回的错误类是BindException.class，所以，在全局拦截器类，应该检测BindException类型的异常，进行特殊处理成返回信息。
     *
     * @param e 绑定验证异常
     * @return 错误返回消息
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ResponseResult validateErrorHandler(org.springframework.validation.BindException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        log.error("参数校验异常:{" + fieldError.getDefaultMessage() + "}({" + fieldError.getField() + "})");
        return ResponseResult.errorResult(HttpResponseCode.PARAMETER_EXCEPTION, fieldError.getDefaultMessage());
    }


    /**
     * 处理绑定异常(传body体，没传body体直接返回系统错误即可)
     * post请求进行jsr303校验时，有两种情况，一种是没传body体，即body都没有；第二种则是参数错误；
     *
     * @param e 绑定验证异常
     * @return {@link ResponseResult}
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseResult handleBindException(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        log.error("参数校验异常:{" + fieldError.getDefaultMessage() + "}({" + fieldError.getField() + "})");
        return ResponseResult.errorResult(HttpResponseCode.PARAMETER_EXCEPTION, fieldError.getDefaultMessage());
    }

}
