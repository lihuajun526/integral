package com.vip.integral.exception;

import com.vip.integral.constant.ExceptionTypeEnum;

/**
 * 协议不匹配异常
 * 
 * @author: Zhou Xuanang
 * @Date: 14:45 16/7/5.
 */
public class ProtocolNotFixException extends SpiderException {
    private static final long serialVersionUID = 1L;

    public ProtocolNotFixException(ExceptionTypeEnum exceptionTypeEnum) {
        super(exceptionTypeEnum.code, exceptionTypeEnum.description);
    }

}
