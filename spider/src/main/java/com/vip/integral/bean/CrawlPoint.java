package com.vip.integral.bean;

/**
 * 采集点
 *
 * @author lihuajun
 */
public class CrawlPoint {

    /*****
     * 采集点属性
     *****/
    private String url;// 采集点url
    private String category;// 分类
    private String tag;
    private boolean isCrawlDetail;// 是否爬取详细页
    private String urlencoded;// url编码
    private String responseType;// 返回类型，JSON/HTML
    private String jsonAnalyzePath;// json解析器路径
    /*****
     * 列表属性
     *****/
    private String listPosRule;// 列表位置规则
    private String listRule;// 列表规则（json）
    private String urlRule;// 链接提取规则
    private String urlScript;// 链接提取脚本，{'by':'js','method':'xxx','code':''}
    /*****
     * 请求属性
     *****/
    private String requestMethod;// 请求类型，GET/POST
    private String cookies;
    private String referer;
    private String accept;
    /*****
     * 分页属性
     *****/
    private String pageIndexPosRule;// 分页位置规则
    private String pageIndexClasspath;// 分页加载器路径

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public boolean isCrawlDetail() {
        return isCrawlDetail;
    }

    public void setCrawlDetail(boolean crawlDetail) {
        isCrawlDetail = crawlDetail;
    }

    public String getUrlencoded() {
        return urlencoded;
    }

    public void setUrlencoded(String urlencoded) {
        this.urlencoded = urlencoded;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public String getJsonAnalyzePath() {
        return jsonAnalyzePath;
    }

    public void setJsonAnalyzePath(String jsonAnalyzePath) {
        this.jsonAnalyzePath = jsonAnalyzePath;
    }

    public String getListPosRule() {
        return listPosRule;
    }

    public void setListPosRule(String listPosRule) {
        this.listPosRule = listPosRule;
    }

    public String getListRule() {
        return listRule;
    }

    public void setListRule(String listRule) {
        this.listRule = listRule;
    }

    public String getUrlRule() {
        return urlRule;
    }

    public void setUrlRule(String urlRule) {
        this.urlRule = urlRule;
    }

    public String getUrlScript() {
        return urlScript;
    }

    public void setUrlScript(String urlScript) {
        this.urlScript = urlScript;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
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

    public String getPageIndexPosRule() {
        return pageIndexPosRule;
    }

    public void setPageIndexPosRule(String pageIndexPosRule) {
        this.pageIndexPosRule = pageIndexPosRule;
    }

    public String getPageIndexClasspath() {
        return pageIndexClasspath;
    }

    public void setPageIndexClasspath(String pageIndexClasspath) {
        this.pageIndexClasspath = pageIndexClasspath;
    }
}
