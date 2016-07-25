package com.vip.integral.bean;

import com.alibaba.fastjson.JSONArray;

/**
 * Created by lihuajun on 16-7-25.
 */
public class TreeNode {

    private Integer id;

    private String text;

    private String state;

    JSONArray attributes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public JSONArray getAttributes() {
        return attributes;
    }

    public void setAttributes(JSONArray attributes) {
        this.attributes = attributes;
    }
}
