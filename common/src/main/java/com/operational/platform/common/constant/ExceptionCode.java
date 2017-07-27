package com.operational.platform.common.constant;

/**
 * 异常类型
 */
public enum ExceptionCode {
    SUCCESS(0, "成功"),
    SYS_ERROR(-10000, "系统错误"),
    PARAM_IS_NULL_ERROR(-11000, "参数为空"),
    Result_IS_NULL_ERROR(-11001, "结果为空"),
    NOT_LOGIN_ERROR(-11002, "未登录"),
    INTEGRAL_NOT_ENOUGH_ERROR(-11003, "积分不足"),
    GET_STAGE_TYPE_ERROR(-11004, "无法获得所属平台"),;

    public final int code;

    public final String description;

    ExceptionCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

}