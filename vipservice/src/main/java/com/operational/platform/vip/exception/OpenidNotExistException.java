package com.operational.platform.vip.exception;

import com.operational.platform.dbservice.constant.ExceptionTypeEnum;

/**
 * 不存在的openid异常
 */
public class OpenidNotExistException extends Exception {
    private static final long serialVersionUID = 1L;

    private String code;
    private String description;

    public OpenidNotExistException(ExceptionTypeEnum exceptionTypeEnum) {
        this.code = exceptionTypeEnum.code;
        this.description = exceptionTypeEnum.description;
    }

    /**
     * errorCode
     *
     * @return
     */
    public String getCode() {
        return code;
    }

    /**
     * 错误描述
     *
     * @return
     */
    public String getDescription() {
        return description;
    }
}
