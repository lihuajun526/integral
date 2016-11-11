package com.operational.platform.spider.component.creater.impl;

import com.alibaba.fastjson.JSONArray;
import com.operational.platform.spider.component.creater.PointLinkCreater;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lihuajun on 16-7-16.
 */
/*
    [
        {
            "urlTpl":"http://list.iqiyi.com/www/{channel}/-{category}---------{postage}-{years}--11-1-1-iqiyi--.html",
            "channel":"电影",
            "path":"1",
            "postage":[{"name":"免费","path":"0"},{"name":"付费","path":"2"}],
            "category":[
                {"name":"喜剧","path":"8"},
                {"name":"悲剧","path":"13"},
                {"name":"爱情","path":"6"},
                {"name":"动作","path":"11"},
                {"name":"枪战","path":"131"},
                {"name":"犯罪","path":"291"},
                {"name":"惊悚","path":"128"},
                {"name":"恐怖","path":"10"},
                {"name":"悬疑","path":"289"},
                {"name":"动画","path":"12"},
                {"name":"家庭","path":"27356"},
                {"name":"奇幻","path":"1284"},
                {"name":"魔幻","path":"129"},
                {"name":"科幻","path":"9"},
                {"name":"战争","path":"7"},
                {"name":"青春","path":"130"}
            ],
            "years":[
                {"name":"2016","path":"2016"},
                {"name":"2015-2011","path":"2011_2015"},
                {"name":"2010-2000","path":"2000_2010"},
                {"name":"90年代","path":"1990_1999"},
                {"name":"80年代","path":"1980_1989"},
                {"name":"更早","path":"1964_1979"}
            ]
        },
        {
            "urlTpl":"http://list.iqiyi.com/www/{channel}/-{category}----------{years}--11-1-1-iqiyi--.html",
            "channel":"电视剧",
            "path":"2",
            "category":[
                {"name":"言情剧","path":"20"},
                {"name":"历史剧","path":"21"},
                {"name":"武侠剧","path":"23"},
                {"name":"古装剧","path":"24"},
                {"name":"年代剧","path":"27"},
                {"name":"农村剧","path":"29"},
                {"name":"偶像剧","path":"30"},
                {"name":"悬疑剧","path":"32"},
                {"name":"科幻剧","path":"34"},
                {"name":"喜剧","path":"135"},
                {"name":"宫廷剧","path":"139"},
                {"name":"商战剧","path":"140"},
                {"name":"神话剧","path":"145"},
                {"name":"穿越剧","path":"148"},
                {"name":"罪案剧","path":"149"},
                {"name":"谍战剧","path":"290"},
                {"name":"青春剧","path":"1653"},
                {"name":"家庭剧","path":"1654"},
                {"name":"军旅剧","path":"1655"},
                {"name":"剧情","path":"24063"},
                {"name":"都市","path":"24064"},
                {"name":"网络剧","path":"24065"}
            ],
            "years":[
                {"name":"2016","path":"2016"},
                {"name":"2015-2011","path":"2011_2015"},
                {"name":"2010-2000","path":"2000_2010"},
                {"name":"90年代","path":"1990_1999"},
                {"name":"80年代","path":"1980_1989"},
                {"name":"更早","path":"1964_1979"}
            ]
        }
    ]
    */
public class AqyPointLinkCreater implements PointLinkCreater {

    private static final Logger LOGGER = LoggerFactory.getLogger(AqyPointLinkCreater.class);

    //private String data = "[{\"urlTpl\":\"http://list.iqiyi.com/www/%s/-%s---------%s-%s--11-{pagenum}-1-iqiyi--.html\",\"channel\":\"电影\",\"path\":\"1\",\"postage\":[{\"name\":\"免费\",\"path\":\"0\"},{\"name\":\"付费\",\"path\":\"2\"}],\"category\":[{\"name\":\"喜剧\",\"path\":\"8\"},{\"name\":\"悲剧\",\"path\":\"13\"},{\"name\":\"爱情\",\"path\":\"6\"},{\"name\":\"动作\",\"path\":\"11\"},{\"name\":\"枪战\",\"path\":\"131\"},{\"name\":\"犯罪\",\"path\":\"291\"},{\"name\":\"惊悚\",\"path\":\"128\"},{\"name\":\"恐怖\",\"path\":\"10\"},{\"name\":\"悬疑\",\"path\":\"289\"},{\"name\":\"动画\",\"path\":\"12\"},{\"name\":\"家庭\",\"path\":\"27356\"},{\"name\":\"奇幻\",\"path\":\"1284\"},{\"name\":\"魔幻\",\"path\":\"129\"},{\"name\":\"科幻\",\"path\":\"9\"},{\"name\":\"战争\",\"path\":\"7\"},{\"name\":\"青春\",\"path\":\"130\"}],\"years\":[{\"name\":\"2016\",\"path\":\"2016\"},{\"name\":\"2015-2011\",\"path\":\"2011_2015\"},{\"name\":\"2010-2000\",\"path\":\"2000_2010\"},{\"name\":\"90年代\",\"path\":\"1990_1999\"},{\"name\":\"80年代\",\"path\":\"1980_1989\"},{\"name\":\"更早\",\"path\":\"1964_1979\"}]}]";
    //去掉2010-2000、90、80、更早这几个年代
    private String data = "[{\"urlTpl\":\"http://list.iqiyi.com/www/%s/-%s---------%s-%s--11-{pagenum}-1-iqiyi--.html\",\"channel\":\"电影\",\"path\":\"1\",\"postage\":[{\"name\":\"免费\",\"path\":\"0\"},{\"name\":\"付费\",\"path\":\"2\"}],\"category\":[{\"name\":\"喜剧\",\"path\":\"8\"},{\"name\":\"悲剧\",\"path\":\"13\"},{\"name\":\"爱情\",\"path\":\"6\"},{\"name\":\"动作\",\"path\":\"11\"},{\"name\":\"枪战\",\"path\":\"131\"},{\"name\":\"犯罪\",\"path\":\"291\"},{\"name\":\"惊悚\",\"path\":\"128\"},{\"name\":\"恐怖\",\"path\":\"10\"},{\"name\":\"悬疑\",\"path\":\"289\"},{\"name\":\"动画\",\"path\":\"12\"},{\"name\":\"家庭\",\"path\":\"27356\"},{\"name\":\"奇幻\",\"path\":\"1284\"},{\"name\":\"魔幻\",\"path\":\"129\"},{\"name\":\"科幻\",\"path\":\"9\"},{\"name\":\"战争\",\"path\":\"7\"},{\"name\":\"青春\",\"path\":\"130\"}],\"years\":[{\"name\":\"2016\",\"path\":\"2016\"},{\"name\":\"2015-2011\",\"path\":\"2011_2015\"}]}]";

    public static void main(String[] args) {
        AqyPointLinkCreater aqyCrawlPoint = new AqyPointLinkCreater();
        aqyCrawlPoint.get("电影");
    }

    /**
     * 查找目标栏目的采集点集合
     *
     * @param targetChannel
     * @return
     */
    public List<Map<String, String>> get(String targetChannel) {

        List<Map<String, String>> linkAttrList = new ArrayList<>();

        List<Classinfo> list = JSONArray.parseArray(data, Classinfo.class);
        for (Classinfo classinfo : list) {
            String channel = classinfo.getChannel();
            if (!targetChannel.equals(channel))
                continue;
            String channelPath = classinfo.getPath();
            for (Attrs attrs : classinfo.getCategory()) {
                String category = attrs.getName();
                String categoryPath = attrs.getPath();
                for (Attrs attrs1 : classinfo.getPostage()) {
                    String postage = attrs1.getName();
                    String postagePath = attrs1.getPath();
                    for (Attrs attrs2 : classinfo.getYears()) {
                        String years = attrs2.getName();
                        String yearsPath = attrs2.getPath();

                        LOGGER.debug(channel + "->" + category + "->" + postage + "->" + years);
                        LOGGER.debug(String.format(classinfo.getUrlTpl(), channelPath, categoryPath, postagePath, yearsPath));

                        Map<String, String> linkAttr = new HashMap<>();
                        linkAttr.put("link",
                                String.format(classinfo.getUrlTpl(), channelPath, categoryPath, postagePath, yearsPath));
                        linkAttr.put("index", channel + "->" + category + "->" + postage + "->" + years);
                        linkAttrList.add(linkAttr);
                    }
                }
            }
        }

        return linkAttrList;
    }
}

class Classinfo {
    private String urlTpl;
    private String channel;
    private String path;
    private List<Attrs> postage;
    private List<Attrs> category;
    private List<Attrs> years;

    public String getUrlTpl() {
        return urlTpl;
    }

    public void setUrlTpl(String urlTpl) {
        this.urlTpl = urlTpl;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<Attrs> getPostage() {
        return postage;
    }

    public void setPostage(List<Attrs> postage) {
        this.postage = postage;
    }

    public List<Attrs> getCategory() {
        return category;
    }

    public void setCategory(List<Attrs> category) {
        this.category = category;
    }

    public List<Attrs> getYears() {
        return years;
    }

    public void setYears(List<Attrs> years) {
        this.years = years;
    }
}

class Attrs {
    private String name;
    private String path;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
