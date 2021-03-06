package com.operational.platform.vip.base;

import com.alibaba.fastjson.JSON;
import com.operational.platform.common.constant.ExceptionCode;
import com.operational.platform.common.constant.ExceptionTypeEnum;

public class Result<T> {

    /**
     * code>=0，成功
     * code<0，失败
     */
    private int code;
    private String message = "";
    private T data;

    public Result() {

    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void set(int code, String message, T data) {
        this.setCode(code);
        this.setMessage(message);
        this.setData(data);
    }

    public void set(int code, String message) {
        this.set(code, message, null);
    }

    public void set(ExceptionCode exceptionCode) {
        this.set(exceptionCode.code, exceptionCode.description, null);
    }


    public void set(String message, T data) {
        this.set(0, message, data);
    }

    public void set(String message) {
        this.set(0, message);
    }

    public String toString() {
        return JSON.toJSONString(this);
    }

}
