package com.bryceli.api.emum;

import lombok.Getter;

/**
 * @author Bryce
 * @date 2020/7/27 12:36
 * @description 返回信息的枚举类，统一返回的规范
 * @Version 1.0
 */
@Getter
public enum ResultCode {
    /**
     * 成功
     */
    SUCCESS(1000,"操作成功"),

    /**
     * 失败
     */
    FAILED(1001, "响应失败"),

    /**
     * 具体的失败类型 TODO 待补充
     */
    VALIDATE_FAILED(1002, "参数校验失败"),

    /**
     * 未知错误
     */
    ERROR(5000, "未知错误");;

    /**
     * 状态码
     */
    private int code;

    /**
     * 状态信息
     */
    private String msg;

    ResultCode(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

}
