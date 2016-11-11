package com.operational.platform.spider.component.creater.impl;

import com.operational.platform.spider.component.creater.PointLinkCreater;

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

        //电影
        //linkAttr.put("link", "https://www.zhihu.com/topic/19550429/followers");

        //男装
        //linkAttr.put("link", "https://www.zhihu.com/topic/19555391/followers");

        //美食
        //linkAttr.put("link", "https://www.zhihu.com/topic/19551137/followers");

        //烘焙
        //linkAttr.put("link", "https://www.zhihu.com/topic/19629004/followers");

        //家居
        //linkAttr.put("link", "https://www.zhihu.com/topic/19559947/followers");

        //烹饪
        //linkAttr.put("link", "https://www.zhihu.com/topic/19551805/followers");

        //互联网
        //linkAttr.put("link", "https://www.zhihu.com/topic/19550517/followers");

        //篮球
        //linkAttr.put("link", "https://www.zhihu.com/topic/19562832/followers");

        //食品安全
        //linkAttr.put("link", "https://www.zhihu.com/topic/19562435/followers");

        //对冲基金
        linkAttr.put("link", "https://www.zhihu.com/topic/19570709/followers");

        linkAttrList.add(linkAttr);

        return linkAttrList;
    }
}
