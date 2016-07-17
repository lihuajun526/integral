package com.vip.integral.exception;

import com.vip.integral.constant.ExceptionTypeEnum;

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
