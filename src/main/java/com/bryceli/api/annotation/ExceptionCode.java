package com.bryceli.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Bryce
 * @date 2020/7/27 17:41
 * @description 如果validation校验失败了，我们可以拿到字段对象并能够获取字段的注解信息
 * 可以让注解直接加在整个类上，让某个类都参数用一个错误码;也可以让注解的值设置为枚举类，这样能够进一步的统一规范……
 * @Version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD}) // 表明该注解只能放在类的字段上
public @interface ExceptionCode {
    // 响应码code
    int value() default 100000;
    // 响应信息msg
    String message() default  "参数校验错误";
}
