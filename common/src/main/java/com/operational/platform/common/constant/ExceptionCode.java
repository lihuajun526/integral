package com.operational.platform.common.constant;

/**
 * 异常类型
 */
public enum ExceptionCode {
    SUCCESS(1, "成功"),
    PARAM_IS_NULL_ERROR(-10000, "参数为空"),
    Result_IS_NULL_ERROR(-10001, "结果为空"),;

    public final int code;

    public final String description;

    ExceptionCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

}