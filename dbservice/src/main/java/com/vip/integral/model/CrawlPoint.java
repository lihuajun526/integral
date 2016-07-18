package com.vip.integral.model;

import java.util.Date;

public class CrawlPoint {

    //采集点属性
    private Integer id;

    private String category;

    private String url;

    private Boolean isCrawlDetail;

    private String jsonAnalyzePath;

    //请求属性
    private String method;

    private String cookies;

    private String referer;

    private String accept;

    private String responseEncode;//返回结果编码

    private String responseType;//返回类型：html/json

    //列表属性
    private String listPosRule;//{"jsoup":""}

    private String listAttrRule;//{"jsoup":"","attr":"",}

    //链接属性
    private String linkRule;

    private String linkSelfRule;

    private String ruleJson;

    //分页属性
    private String pageIndexRule;

    private String pageIndexClassPath;

    private Date createTime;

    private Date updateTime;

}