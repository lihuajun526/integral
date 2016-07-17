package com.vip.integral.exception;

import com.vip.integral.constant.ExceptionTypeEnum;

/**
 * 请求异常
 *
 * @author: Zhou Xuanang
 * @Date: 14:04 16/7/5.
 */
public class RequestException extends SpiderException {
    private static final long serialVersionUID = 1L;

    public RequestException(ExceptionTypeEnum exceptionTypeEnum) {
        super(exceptionTypeEnum.code, exceptionTypeEnum.description);
    }
}
