package com.operational.platform.dbservice.exception;

import com.operational.platform.dbservice.constant.ExceptionTypeEnum;

/**
 * 订单异常
 */
public class OrderException extends Exception {
    private static final long serialVersionUID = 1L;

    private String code;
    private String description;

    public OrderException(ExceptionTypeEnum exceptionTypeEnum) {
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
