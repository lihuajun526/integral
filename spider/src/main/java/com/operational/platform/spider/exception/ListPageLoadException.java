package com.operational.platform.spider.exception;

import com.operational.platform.spider.constant.ExceptionTypeEnum;

/**
 * 列表页获取异常
 * 
 * @author: Zhou Xuanang
 * @Date: 14:21 16/7/5.
 */
public class ListPageLoadException extends SpiderException {
    private static final long serialVersionUID = 1L;

    public ListPageLoadException(ExceptionTypeEnum exceptionTypeEnum) {
        super(exceptionTypeEnum.code, exceptionTypeEnum.description);
    }

}
