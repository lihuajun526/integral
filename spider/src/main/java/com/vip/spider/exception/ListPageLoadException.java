package com.vip.spider.exception;

import com.vip.spider.constant.ExceptionTypeEnum;

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
