package com.operational.platform.spider.exception;

import com.operational.platform.spider.constant.ExceptionTypeEnum;

/**
 * 请求异常
 *
 * @author: Zhou Xuanang
 * @Date: 14:04 16/7/5.
 */
public class GetCommentException extends CommentException {
    private static final long serialVersionUID = 1L;

    public GetCommentException(ExceptionTypeEnum exceptionTypeEnum) {
        super(exceptionTypeEnum.code, exceptionTypeEnum.description);
    }
}
