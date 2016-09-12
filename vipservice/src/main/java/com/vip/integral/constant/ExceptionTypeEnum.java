package com.vip.integral.constant;

/**
 * 异常类型
 *
 * @author: Zhou Xuanang
 * @Date: 10:07 16/6/27.
 */
public enum ExceptionTypeEnum {
    SUCCESS("000000", "成功!"),
    HTTP_REQUEST_ERROR("000001", "http请求失败!")
    ;

    public final String code;

    public final String description;

    ExceptionTypeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

}