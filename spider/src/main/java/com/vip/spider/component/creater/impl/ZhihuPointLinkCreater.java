package com.vip.spider.component.creater.impl;

import com.vip.spider.component.creater.PointLinkCreater;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lihuajun on 2016/10/21.
 */
public class ZhihuPointLinkCreater implements PointLinkCreater {
    @Override
    public List<Map<String, String>> get(String channel) {


        List<Map<String, String>> linkAttrList = new ArrayList<>();

        Map<String, String> linkAttr = new HashMap<>();
        linkAttr.put("link", "https://www.zhihu.com/topic/19550429/followers");

        linkAttrList.add(linkAttr);

        return linkAttrList;
    }
}
