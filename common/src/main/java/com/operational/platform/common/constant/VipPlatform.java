package com.operational.platform.common.constant;

public enum VipPlatform {

    Youku("youku", "youku.com"),
    Iqy("iqy", "iqyi.com"),
    Txsp("txsp", "qq.com"),;

    public final String name;
    public final String domain;

    VipPlatform(String name, String domain) {
        this.name = name;
        this.domain = domain;
    }

}