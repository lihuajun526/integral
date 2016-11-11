package com.operational.platform.taskbreak.constant;

/**
 * @author: Zhou Xuanang
 * @Date: 15:05 2016/11/7.
 */
public enum TaskStatusEnum {
    DONE("000000", "分解完成"),
    PROCESSING("000001", "正在分解");

    public final String code;
    public final String description;

    TaskStatusEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
