package com.operational.platform.dbservice.model;

import java.util.Date;

public class CrawlPoint {
    private Integer id;

    private Integer nodeid;

    private String category;

    private String url;

    private String urlCrClasspath;

    private Integer isCrawlDetail;

    private String jsonAnalyzePath;

    private Integer status;

    private String belong;

    private Integer maxPage;

    private Long sleepTime;

    private String method;

    private String postParam;

    private String header;

    private String cookies;

    private String referer;

    private String accept;

    private String responseEncode;

    private String responseHandler;

    private String responseType;

    private String listRecordRule;

    private String recordCountRule;

    private String listAttrRule;

    private String linkRule;

    private String linkSelfRule;

    private String pageIndexRule;

    private String pageIndexClasspath;

    private String taskClasspath;

    private String attr;

    private String pageRule;

    private Date createTime;

    private Date updateTime;

    public void setPageRule(String pageRule) {
        this.pageRule = pageRule;
    }

    public String getPageRule() {
        return pageRule;
    }

    public String getTaskClasspath() {
        return taskClasspath;
    }

    public void setTaskClasspath(String taskClasspath) {
        this.taskClasspath = taskClasspath;
    }

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNodeid() {
        return nodeid;
    }

    public void setNodeid(Integer nodeid) {
        this.nodeid = nodeid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getUrlCrClasspath() {
        return urlCrClasspath;
    }

    public void setUrlCrClasspath(String urlCrClasspath) {
        this.urlCrClasspath = urlCrClasspath == null ? null : urlCrClasspath.trim();
    }

    public Integer getIsCrawlDetail() {
        return isCrawlDetail;
    }

    public void setIsCrawlDetail(Integer isCrawlDetail) {
        this.isCrawlDetail = isCrawlDetail;
    }

    public String getJsonAnalyzePath() {
        return jsonAnalyzePath;
    }

    public void setJsonAnalyzePath(String jsonAnalyzePath) {
        this.jsonAnalyzePath = jsonAnalyzePath == null ? null : jsonAnalyzePath.trim();
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
        this.belong = belong == null ? null : belong.trim();
    }

    public Integer getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(Integer maxPage) {
        this.maxPage = maxPage;
    }

    public Long getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(Long sleepTime) {
        this.sleepTime = sleepTime;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    public String getCookies() {
        return cookies;
    }

    public void setCookies(String cookies) {
        this.cookies = cookies == null ? null : cookies.trim();
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer == null ? null : referer.trim();
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept == null ? null : accept.trim();
    }

    public String getResponseEncode() {
        return responseEncode;
    }

    public void setResponseEncode(String responseEncode) {
        this.responseEncode = responseEncode == null ? null : responseEncode.trim();
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType == null ? null : responseType.trim();
    }

    public String getListRecordRule() {
        return listRecordRule;
    }

    public void setListRecordRule(String listRecordRule) {
        this.listRecordRule = listRecordRule == null ? null : listRecordRule.trim();
    }

    public String getRecordCountRule() {
        return recordCountRule;
    }

    public void setRecordCountRule(String recordCountRule) {
        this.recordCountRule = recordCountRule;
    }

    public String getListAttrRule() {
        return listAttrRule;
    }

    public void setListAttrRule(String listAttrRule) {
        this.listAttrRule = listAttrRule == null ? null : listAttrRule.trim();
    }

    public String getLinkRule() {
        return linkRule;
    }

    public void setLinkRule(String linkRule) {
        this.linkRule = linkRule == null ? null : linkRule.trim();
    }

    public String getLinkSelfRule() {
        return linkSelfRule;
    }

    public void setLinkSelfRule(String linkSelfRule) {
        this.linkSelfRule = linkSelfRule == null ? null : linkSelfRule.trim();
    }

    public String getPageIndexRule() {
        return pageIndexRule;
    }

    public void setPageIndexRule(String pageIndexRule) {
        this.pageIndexRule = pageIndexRule == null ? null : pageIndexRule.trim();
    }

    public String getPageIndexClasspath() {
        return pageIndexClasspath;
    }

    public void setPageIndexClasspath(String pageIndexClasspath) {
        this.pageIndexClasspath = pageIndexClasspath == null ? null : pageIndexClasspath.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
}