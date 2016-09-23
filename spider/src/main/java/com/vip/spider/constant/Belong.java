package com.vip.spider.constant;

/**
 * Created by lihuajun on 16-7-27.
 */
public enum Belong {

    AQY("aqy"),
    YouKu("youku"),
    TXXW("txxw"),
    QZONE("qzone"),
    ;

    private String value;

    Belong(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

}
