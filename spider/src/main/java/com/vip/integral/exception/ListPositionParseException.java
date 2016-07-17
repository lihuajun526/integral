package com.vip.integral.exception;

import com.vip.integral.constant.ExceptionTypeEnum;

/**
 * 优惠信息列表位置解析异常
 * 
 * @author: Zhou Xuanang
 * @Date: 10:53 16/6/27.
 */
public class ListPositionParseException extends SpiderException {
    private static final long serialVersionUID = 1L;

    public ListPositionParseException(ExceptionTypeEnum exceptionTypeEnum) {
        super(exceptionTypeEnum.code, exceptionTypeEnum.description);
    }

}
