package com.operational.platform.common.exception;


public class String2TxtException extends Exception {
    private static final long serialVersionUID = 1L;

    private String code;
    private String description;

    public String2TxtException() {
    }

    public String2TxtException(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
