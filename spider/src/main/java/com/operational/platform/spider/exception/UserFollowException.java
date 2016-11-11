package com.operational.platform.spider.exception;

import com.operational.platform.spider.constant.ExceptionTypeEnum;

/**
 * 请求异常
 *
 * @author: Zhou Xuanang
 * @Date: 14:04 16/7/5.
 */
public class UserFollowException extends SpiderException {
    private static final long serialVersionUID = 1L;

    public UserFollowException(ExceptionTypeEnum exceptionTypeEnum) {
        super(exceptionTypeEnum.code, exceptionTypeEnum.description);
    }

    public UserFollowException(String code, String description) {
        super(code, description);
    }
}
