package com.operational.platform.taskbreak.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lihuajun on 2017/9/27.
 */
public class ListPage {

    private Integer pageIndex;
    private String content;
    private Map<String,String> attr = new HashMap<>();

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Map<String, String> getAttr() {
        return attr;
    }

    public void setAttr(Map<String, String> attr) {
        this.attr = attr;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
