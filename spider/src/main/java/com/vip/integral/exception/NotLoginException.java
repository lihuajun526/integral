package com.vip.integral.exception;

import com.vip.integral.constant.ExceptionTypeEnum;

/**
 * 列表页获取异常
 *
 * @author: Zhou Xuanang
 * @Date: 14:21 16/7/5.
 */
public class NotLoginException extends SpiderException {
    private static final long serialVersionUID = 1L;

    public NotLoginException() {
        super(ExceptionTypeEnum.NOT_LOGIN_ERROR.code, ExceptionTypeEnum.NOT_LOGIN_ERROR.description);
    }

}
