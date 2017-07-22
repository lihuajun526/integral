package com.operational.platform.common.constant;

/**
 * 异常类型
 */
public enum ExceptionCode {
    SUCCESS(0, "成功"),
    SYS_ERROR(-10000, "系统错误"),
    PARAM_IS_NULL_ERROR(-11000, "参数为空"),
    Result_IS_NULL_ERROR(-11001, "结果为空"),
    NOT_LOGIN_ERROR(-11002, "未登录"),;

    public final int code;

    public final String description;

    ExceptionCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

}