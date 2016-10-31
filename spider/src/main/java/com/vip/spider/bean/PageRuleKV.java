package com.vip.spider.bean;

/**
 * Created by lihuajun on 2016/10/31.
 */
public class PageRuleKV {
    private String key;
    private String posRule;
    private String jsoup;
    private String attr;
    private String regex;
    private String linkRegex;
    private String script;
    private PageRule child;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

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

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public PageRule getChild() {
        return child;
    }

    public void setChild(PageRule child) {
        this.child = child;
    }

    public String getLinkRegex() {
        return linkRegex;
    }

    public void setLinkRegex(String linkRegex) {
        this.linkRegex = linkRegex;
    }

    public String getPosRule() {
        return posRule;
    }

    public void setPosRule(String posRule) {
        this.posRule = posRule;
    }

}
