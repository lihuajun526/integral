package com.vip.integral.exception;

import com.vip.integral.constant.ExceptionTypeEnum;

/**
 * 优惠详情URL解析异常
 * 
 * @author: Zhou Xuanang
 * @Date: 10:03 16/6/27.
 */
public class FavourUrlParseException extends SpiderException {
    private static final long serialVersionUID = 1L;

    public FavourUrlParseException(ExceptionTypeEnum exceptionTypeEnum) {
        super(exceptionTypeEnum.code, exceptionTypeEnum.description);
    }

}
