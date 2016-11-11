package com.operational.platform.spider.exception;

import com.operational.platform.spider.constant.ExceptionTypeEnum;

/**
 * 请求异常
 *
 * @author: Zhou Xuanang
 * @Date: 14:04 16/7/5.
 */
public class MessageSendException extends SpiderException {
    private static final long serialVersionUID = 1L;

    public MessageSendException(ExceptionTypeEnum exceptionTypeEnum) {
        super(exceptionTypeEnum.code, exceptionTypeEnum.description);
    }

    public MessageSendException(String code, String description) {
        super(code, description);
    }
}
