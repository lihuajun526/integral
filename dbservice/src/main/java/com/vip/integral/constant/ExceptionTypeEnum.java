package com.vip.integral.constant;

/**
 * 异常类型
 */
public enum ExceptionTypeEnum {
    SUCCESS("000000", "成功!"),
    NOT_IN_SELL_TIME_ERROR("000001", "不在开售时间范围"),
    STOCKS_LOW_ERROR("000002", "库存不足"),
    BALANCE_LOW_ERROR("000003","账户余额不足"),
    TODAY_HAS_BUY_ERROR("000004","用户今日已经买过")
    ;

    public final String code;

    public final String description;

    ExceptionTypeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

}