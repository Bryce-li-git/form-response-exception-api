package com.bryceli.api.config;

import com.bryceli.api.annotation.ExceptionCode;
import com.bryceli.api.emum.ResultCode;
import com.bryceli.api.exception.APIException;
import com.bryceli.api.vo.ResultVO;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Field;

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
    public ResultVO<String> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) throws NoSuchFieldException {
        // 从异常对象中拿到错误信息
        String defaultMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();

        // 参数的Class对象，等下好通过字段名称获取Field对象
        Class<?> parameterType = e.getParameter().getParameterType();
        // 拿到错误的字段名称
        String fieldName = e.getBindingResult().getFieldError().getField();
        Field field = parameterType.getDeclaredField(fieldName);
        // 获取Field对象上的自定义注解
        ExceptionCode annotation = field.getAnnotation(ExceptionCode.class);

        // 有注解的话就返回注解的响应信息
        if (annotation != null) {
            return new ResultVO<>(annotation.value(),annotation.message(),defaultMessage);
        }

        // 没有注解就提取错误提示信息进行返回统一错误码
        return new ResultVO<>(ResultCode.VALIDATE_FAILED, defaultMessage);
    }

    @ExceptionHandler(APIException.class)
    public ResultVO<String> APIExceptionHandler(APIException e) {
        // 注意哦，这里传递的响应码枚举
        return new ResultVO<>(ResultCode.FAILED, e.getMsg());
    }
}
