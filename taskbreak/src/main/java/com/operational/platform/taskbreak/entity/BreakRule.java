package com.operational.platform.taskbreak.entity;

/**
 * @author: Zhou Xuanang
 * @Date: 17:12 2016/11/3.
 */
public class BreakRule {
    private String jsoup;
    private String attr;
    private String regex;

    public String getJsoup() {
        return jsoup;
    }

    public void setJsoup(String jsoup) {

        this.jsoup = jsoup;
    }

    public String getAttr() {

        return attr;
    }

    public void setAttr(String attr) {

        this.attr = attr;
    }

    public String getRegex() {

        return regex;
    }

    public void setRegex(String regex) {

        this.regex = regex;
    }
}
