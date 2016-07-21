package com.vip.integral.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lihuajun on 16-7-21.
 */
public class Comment {

    private Map<String,String> attr = new HashMap<>();

    public Map<String, String> getAttr() {
        return attr;
    }

    public void setAttr(Map<String, String> attr) {
        this.attr = attr;
    }
}
