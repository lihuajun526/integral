package com.unionpay.spiderframework.proxyservice.entity;

/**
 * @author: Zhou Xuanang
 * @Date: 11:06 2016/11/11.
 */
public class ThreadResult {
    private Object result;
    private Exception exception;

    public ThreadResult(Object result) {
        this.result = result;
    }

    public ThreadResult(Exception exception) {
        this.exception = exception;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}
