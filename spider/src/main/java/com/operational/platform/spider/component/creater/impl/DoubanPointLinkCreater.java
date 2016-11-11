package com.operational.platform.spider.component.creater.impl;

import com.operational.platform.spider.component.creater.PointLinkCreater;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lihuajun on 2016/10/21.
 */
public class DoubanPointLinkCreater implements PointLinkCreater {
    @Override
    public List<Map<String, String>> get(String channel) {

        String[] strs = {"热门", "最新", "经典", "可播放", "豆瓣高分", "冷门佳片", "华语", "欧美", "韩国", "日本", "动作", "喜剧", "爱情", "科幻", "悬疑", "恐怖", "动画"};
        for (String str : strs) {

        }

        List<Map<String, String>> linkAttrList = new ArrayList<>();
        Map<String, String> linkAttr = new HashMap<>();
        //linkAttr.put("link", );
        linkAttrList.add(linkAttr);

        return linkAttrList;
    }
}
