package com.operational.platform.common.constant;

/**
 * 异常类型
 */
public enum VipPlatform {

    Youku("youku.com"),
    Iqy("iqyi.com"),
    Txsp("qq.com"),;


    public final String domain;

    VipPlatform(String domain) {
        this.domain = domain;
    }

}