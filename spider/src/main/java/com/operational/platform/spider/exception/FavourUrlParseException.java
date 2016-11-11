package com.operational.platform.spider.exception;

import com.operational.platform.spider.constant.ExceptionTypeEnum;

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
