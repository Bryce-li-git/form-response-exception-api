package com.bryceli.api.config;

import com.bryceli.api.emum.ResultCode;
import com.bryceli.api.exception.APIException;
import com.bryceli.api.vo.ResultVO;
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
    public ResultVO<String> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        // 注意哦，这里传递的响应码枚举
        return new ResultVO<>(ResultCode.VALIDATE_FAILED, objectError.getDefaultMessage());
    }

    @ExceptionHandler(APIException.class)
    public ResultVO<String> APIExceptionHandler(APIException e) {
        // 注意哦，这里传递的响应码枚举
        return new ResultVO<>(ResultCode.FAILED, e.getMsg());
    }
}
