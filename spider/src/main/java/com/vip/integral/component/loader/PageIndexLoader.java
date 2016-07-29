package com.vip.integral.component.loader;

import com.vip.integral.bean.CrawlPointAttr;
import com.vip.integral.exception.ElementNotExistException;
import com.vip.integral.exception.RequestException;
import com.vip.integral.util.StrUtil;
import com.vip.integral.util.XHttpClient;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public abstract class PageIndexLoader {

    protected static final Logger LOGGER = LoggerFactory.getLogger(PageIndexLoader.class);

    protected Integer pageCount = 1;// 总页数
    protected Integer curCount = 0;// 当前页
    protected CrawlPointAttr crawlPointAttr;
    public HttpUriRequest httpUriRequest;
    private List<NameValuePair> postParams = new ArrayList<NameValuePair>();
    protected String url;
    protected String pageIndexUrl;
    protected String order = "ASC";
    protected boolean isStartZero = false;//页面导航是否从0开始
    private HttpPost httpPost;
    private HttpGet httpGet;

    /**
     * 初始化
     * @param crawlPointAttr
     * @throws UnsupportedEncodingException
     */
    public void init(CrawlPointAttr crawlPointAttr) throws UnsupportedEncodingException {
        this.crawlPointAttr = crawlPointAttr;
        if ("POST".equalsIgnoreCase(crawlPointAttr.getMethod())) {
            httpPost = new HttpPost(crawlPointAttr.getUrl());
            httpUriRequest = httpPost;
        } else if ("GET".equalsIgnoreCase(crawlPointAttr.getMethod())) {
            url = URLDecoder.decode(crawlPointAttr.getUrl(), "utf-8");
            url = url.replace("{pagenum}", "%s");
            httpGet = new HttpGet();
            httpUriRequest = httpGet;
        }
        // 设置请求Header
        if (!StringUtils.isEmpty(crawlPointAttr.getAccept()))
            httpUriRequest.setHeader("Accept", crawlPointAttr.getAccept());
        if (!StringUtils.isEmpty(crawlPointAttr.getReferer()))
            httpUriRequest.setHeader("Referer", crawlPointAttr.getReferer());
    }

    public boolean isNext() {
        if (curCount < pageCount)
            return true;
        return false;
    }

    /**
     * 下一页
     *
     * @return
     * @throws URISyntaxException
     * @throws RequestException
     */
    public String next() throws URISyntaxException, RequestException, ElementNotExistException {

        if ("POST".equalsIgnoreCase(crawlPointAttr.getMethod())) {// POST请求

        } else if ("GET".equalsIgnoreCase(httpUriRequest.getMethod())) {// GET请求

            if (pageIndexUrl != null) {
                if ("DESC".equalsIgnoreCase(order)) {
                    httpGet.setURI(new URI(String.format(pageIndexUrl, --pageCount)));
                    curCount++;
                } else {
                    httpGet.setURI(new URI(String.format(pageIndexUrl, isStartZero ? curCount++ : ++curCount)));
                }
            } else {
                String reqUri;
                if ("DESC".equalsIgnoreCase(order)) {
                    reqUri = String.format(url, --pageCount);
                    curCount++;
                } else {
                    reqUri = String.format(url, isStartZero ? curCount++ : ++curCount);
                }
                if (reqUri.indexOf("?") != -1) {
                    String[] strs = reqUri.split("\\?");
                    reqUri = strs[0] + "?" + StrUtil.encode(strs[1]);
                }
                httpGet.setURI(new URI(reqUri));
            }
        }
        String response = XHttpClient.doRequest(httpUriRequest);
        // 更新总页数
        updatePageCount(response);
        // 返回
        return response;
    }

    /**
     * 更新总页数
     *
     * @param response
     */
    public abstract void updatePageCount(String response) throws ElementNotExistException;

}
