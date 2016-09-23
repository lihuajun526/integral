package com.vip.spider.exception;

/**
 * 爬虫异常
 * 
 * @author: Zhou Xuanang
 * @Date: 14:04 16/7/5.
 */
public class SpiderException extends Exception {
    private static final long serialVersionUID = 1L;

    private String code;
    private String description;

    public SpiderException(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * errorCode
     * 
     * @return
     */
    public String getCode() {
        return code;
    }

    /**
     * 错误描述
     *
     * @return
     */
    public String getDescription() {
        return description;
    }
}
