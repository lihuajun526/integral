package com.unionpay.spiderframework.proxyservice.entity;

/**
 * @author: Zhou Xuanang
 * @Date: 14:01 2016/11/9.
 */
public class MonitorQualitySource {
    private String name;
    private String url;
    private Integer expectResponseSeconds;

    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getUrl() {

        return url;
    }

    public void setUrl(String url) {

        this.url = url;
    }

    public Integer getExpectResponseSeconds() {
        return expectResponseSeconds;
    }

    public void setExpectResponseSeconds(Integer expectResponseSeconds) {
        this.expectResponseSeconds = expectResponseSeconds;
    }
}
