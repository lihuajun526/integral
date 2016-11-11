package com.operational.platform.spider.exception;

import com.operational.platform.spider.constant.ExceptionTypeEnum;

/**
 * 优惠详情页获取异常
 * 
 * @author: Zhou Xuanang
 * @Date: 14:25 16/7/5.
 */
public class FavourPageLoadException extends SpiderException {
    private static final long serialVersionUID = 1L;

    public FavourPageLoadException(ExceptionTypeEnum exceptionTypeEnum) {
        super(exceptionTypeEnum.code, exceptionTypeEnum.description);
    }

}
