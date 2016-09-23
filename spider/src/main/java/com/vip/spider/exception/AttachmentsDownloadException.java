package com.vip.spider.exception;

import com.vip.spider.constant.ExceptionTypeEnum;

/**
 * 附件下载异常
 * 
 * @author: Zhou Xuanang
 * @Date: 14:44 16/7/5.
 */
public class AttachmentsDownloadException extends SpiderException {
    private static final long serialVersionUID = 1L;

    public AttachmentsDownloadException(ExceptionTypeEnum exceptionTypeEnum) {
        super(exceptionTypeEnum.code, exceptionTypeEnum.description);
    }

}
