package com.vip.integral.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lihuajun on 16-6-21.
 */
public class ParseResult {

    private String category;// 分类
    private String link;
    private Map<String, String> attr = new HashMap<>();

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Map<String, String> getAttr() {
        return attr;
    }

    public void setAttr(Map<String, String> attr) {
        this.attr = attr;
    }
}
