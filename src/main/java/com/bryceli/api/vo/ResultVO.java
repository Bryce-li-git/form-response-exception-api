package com.bryceli.api.vo;

import com.bryceli.api.emum.ResultCode;
import lombok.Getter;

/**
 * @author Bryce
 * @date 2020/7/27 12:31
 * @description 返回信息的泛型类
 * @Version 1.0
 */
@Getter
public class ResultVO<T> {
    /**
     * 响应状态码
     */
    private int code;

    /**
     * 响应提示信息
     */
    private String msg;

    /**
     * 具体响应数据
     */
    private T data;

    /**
     * 默认构造函数，返回成功的信息
     * @param data
     */
    public ResultVO(T data){
        this(ResultCode.SUCCESS,data);
    }

    public ResultVO(ResultCode resultCode,T data){
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
        this.data = data;
    }
    public ResultVO(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
