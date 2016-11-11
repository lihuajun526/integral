package com.operational.platform.spider.exception;

import com.operational.platform.spider.constant.ExceptionTypeEnum;

/**
 * 列表记录解析异常
 *
 * @author: Zhou Xuanang
 * @Date: 10:53 16/6/27.
 */
public class ListRecordParseException extends SpiderException {
    private static final long serialVersionUID = 1L;

    public ListRecordParseException(ExceptionTypeEnum exceptionTypeEnum) {
        super(exceptionTypeEnum.code, exceptionTypeEnum.description);
    }

}
