package com.operational.platform.spider.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lihuajun on 16-7-21.
 */
public class Comment {

    private String id;
    private Map<String, String> attr = new HashMap<>();
    private List<Comment> replys = new ArrayList<>();

    public Map<String, String> getAttr() {
        return attr;
    }

    public void setAttr(Map<String, String> attr) {
        this.attr = attr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Comment> getReplys() {
        return replys;
    }

    public void setReplys(List<Comment> replys) {
        this.replys = replys;
    }
}
