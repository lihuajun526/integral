package com.operational.platform.spider.exception;

import com.operational.platform.spider.constant.ExceptionTypeEnum;

/**
 * 列表参数解析异常
 * 
 * @author: Zhou Xuanang
 * @Date: 10:03 16/6/27.
 */
public class ListParamsParseException extends SpiderException {
    private static final long serialVersionUID = 1L;

    public ListParamsParseException(ExceptionTypeEnum exceptionTypeEnum) {
        super(exceptionTypeEnum.code, exceptionTypeEnum.description);
    }

}
