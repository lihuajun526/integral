package com.operational.platform.spider.exception;

/**
 * 解析过程中dom不存在异常
 */
public class ElementNotExistException extends SpiderException {
    private static final long serialVersionUID = 1L;

    public ElementNotExistException(String code, String description) {
        super(code, description);
    }

}
