package com.bryceli.api.config;

import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Bryce
 * @date 2020/7/27 16:58
 * @description 全局异常处理类
 * @Version 1.0
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {

    /**
     * 在方法上加上@ExceptionHandler注解并指定你想处理的异常类型，
     * 接着在方法内编写对该异常的操作逻辑，就完成了对该异常的全局处理
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        // 然后提取错误提示信息进行返回
        return objectError.getDefaultMessage();
    }
}
