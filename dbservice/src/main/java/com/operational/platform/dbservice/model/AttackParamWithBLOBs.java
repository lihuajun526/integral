package com.operational.platform.dbservice.model;

public class AttackParamWithBLOBs extends AttackParam {
    private String data;

    private String cookies;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data == null ? null : data.trim();
    }

    public String getCookies() {
        return cookies;
    }

    public void setCookies(String cookies) {
        this.cookies = cookies == null ? null : cookies.trim();
    }
}