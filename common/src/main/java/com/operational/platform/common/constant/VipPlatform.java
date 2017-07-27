package com.operational.platform.common.constant;

public enum VipPlatform {

    Youku("youku", "youku.com"),
    Iqy("iqy", "iqiyi.com"),
    Txsp("txsp", "qq.com"),
    PPLive("pplive", "pptv.com"),
    Mgtv("mgtv", "mgtv.com"),
    Ls("ls", "le.com"),
    Souhu("souhu", "sohu.com"),
    Pplive("pplive", "pptv.com"),;

    public final String name;
    public final String domain;

    VipPlatform(String name, String domain) {
        this.name = name;
        this.domain = domain;
    }

}