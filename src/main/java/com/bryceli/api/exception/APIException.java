package com.bryceli.api.exception;

import lombok.Getter;

/**
 * @author Bryce
 * @date 2020/7/27 17:17
 * @description TODO
 * @Version 1.0
 */
@Getter //只要getter方法，无需setter
public class APIException extends RuntimeException {
    private int code;
    private String msg;

    public APIException() {
        this(1001, "接口错误");
    }

    public APIException(String msg) {
        this(1001, msg);
    }

    public APIException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
