package com.operational.platform.common.exception;


import com.operational.platform.common.constant.ExceptionTypeEnum;

/**
 * Created by lihuajun on 17-1-22.
 */
public class CommonException extends Exception {

    private String code;
    private String desc;

    public CommonException(ExceptionTypeEnum exceptionTypeEnum) {
        this.code = exceptionTypeEnum.getCode();
        this.desc = exceptionTypeEnum.getDescription();
    }

    public CommonException(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public CommonException(String desc) {
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
