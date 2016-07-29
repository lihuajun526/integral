package com.vip.integral.bean;

/**
 * 采集点
 *
 * @author lihuajun
 */
public class CrawlPointAttr {

    //采集点属性
    private Integer id;

    private String category;

    private String url;

    private String urlCrClassPath;

    private Boolean isCrawlDetail;

    private String jsonAnalyzePath;

    private Integer status;

    private String belong;//所属源

    private Integer maxPage;

    public Integer getMaxPage() {
        if (maxPage == null)
            return Integer.MAX_VALUE;
        else
            return maxPage;
    }

    public void setMaxPage(Integer maxPage) {
        this.maxPage = maxPage;
    }

    //请求属性
    private String method;

    private String cookies;

    private String referer;

    private String accept;

    private String responseEncode;//返回结果编码

    private String responseType;//返回类型：html/json

    //列表属性
    private String listRecordRule;//{"jsoup":""}

    private String listAttrRule;//{"jsoup":"","attr":"",}

    //链接属性
    private String linkRule;

    private String linkSelfRule;

    //分页属性
    private String pageIndexRule;

    private String pageIndexClassPath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
