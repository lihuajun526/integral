package com.vip.spider.bean;

/**
 * 采集点
 *
 * @author lihuajun
 */
public class CrawlPointAttr {

    //采集点属性
    private Integer id;

    private String taskid;

    private String category;

    private String url;

    private String urlCrClassPath;

    private Boolean isCrawlDetail;

    private String jsonAnalyzePath;

    private Integer status;

    private String belong;//所属源

    private Integer maxPage;

    private String taskClasspath;

    private String attr;

    private Long sleepTime;

    public String getTaskClasspath() {
        return taskClasspath;
    }

    public void setTaskClasspath(String taskClasspath) {
        this.taskClasspath = taskClasspath;
    }

    public Integer getMaxPage() {
        if (maxPage == null)
            return Integer.MAX_VALUE;
        else
            return maxPage;
    }

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    public void setMaxPage(Integer maxPage) {
        this.maxPage = maxPage;
    }

    //请求属性
    private String method;

    private String postParam;

    private String header;

    private String cookies;

    private String referer;

    private String accept;

    private String responseEncode;//返回结果编码

    private String responseType;//返回类型：html/json

    private String responseHandler;

    //列表属性
    private String listRecordRule;//{"jsoup":""}

    private String listAttrRule;//{"jsoup":"","attr":"",}

    //链接属性
    private String linkRule;

    private String linkSelfRule;

    //分页属性
    private String pageIndexRule;

    private String pageIndexClassPath;

    //详细页规则
    private String pageRule;

    public void setPageRule(String pageRule) {
        this.pageRule = pageRule;
    }

    public String getPageRule() {
        return pageRule;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlCrClassPath() {
        return urlCrClassPath;
    }

    public void setUrlCrClassPath(String urlCrClassPath) {
        this.urlCrClassPath = urlCrClassPath;
    }

    public Boolean getCrawlDetail() {
        return isCrawlDetail;
    }

    public void setCrawlDetail(Boolean crawlDetail) {
        isCrawlDetail = crawlDetail;
    }

    public String getJsonAnalyzePath() {
        return jsonAnalyzePath;
    }

    public void setJsonAnalyzePath(String jsonAnalyzePath) {
        this.jsonAnalyzePath = jsonAnalyzePath;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getCookies() {
        return cookies;
    }

    public void setCookies(String cookies) {
        this.cookies = cookies;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }

    public String getResponseEncode() {
        return responseEncode;
    }

    public void setResponseEncode(String responseEncode) {
        this.responseEncode = responseEncode;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public String getListRecordRule() {
        return listRecordRule;
    }

    public void setListRecordRule(String listRecordRule) {
        this.listRecordRule = listRecordRule;
    }

    public String getListAttrRule() {
        return listAttrRule;
    }

    public void setListAttrRule(String listAttrRule) {
        this.listAttrRule = listAttrRule;
    }

    public String getLinkRule() {
        return linkRule;
    }

    public void setLinkRule(String linkRule) {
        this.linkRule = linkRule;
    }

    public String getLinkSelfRule() {
        return linkSelfRule;
    }

    public void setLinkSelfRule(String linkSelfRule) {
        this.linkSelfRule = linkSelfRule;
    }

    public String getPageIndexRule() {
        return pageIndexRule;
    }

    public void setPageIndexRule(String pageIndexRule) {
        this.pageIndexRule = pageIndexRule;
    }

    public String getPageIndexClassPath() {
        return pageIndexClassPath;
    }

    public void setPageIndexClassPath(String pageIndexClassPath) {
        this.pageIndexClassPath = pageIndexClassPath;
    }

    public void setPostParam(String postParam) {
        this.postParam = postParam;
    }

    public String getPostParam() {
        return postParam;
    }

    public void setResponseHandler(String responseHandler) {
        this.responseHandler = responseHandler;
    }

    public String getResponseHandler() {
        return responseHandler;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getHeader() {
        return header;
    }

    public void setSleepTime(Long sleepTime) {
        this.sleepTime = sleepTime;
    }

    public Long getSleepTime() {
        return sleepTime;
    }
}
