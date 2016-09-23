package com.vip.spider.exception;

import com.vip.spider.constant.ExceptionTypeEnum;

/**
 * 优惠详情页解析异常
 * 
 * @author: Zhou Xuanang
 * @Date: 14:34 16/7/5.
 */
public class FavourPageParseException extends SpiderException {
    private static final long serialVersionUID = 1L;

    public FavourPageParseException(ExceptionTypeEnum exceptionTypeEnum) {
        super(exceptionTypeEnum.code, exceptionTypeEnum.description);
    }

}
