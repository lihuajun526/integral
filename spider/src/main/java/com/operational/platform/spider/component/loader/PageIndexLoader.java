package com.operational.platform.spider.component.loader;

import com.operational.platform.spider.exception.RequestException;
import com.operational.platform.spider.util.StrUtil;
import com.operational.platform.spider.util.XHttpClient;
import com.operational.platform.spider.util.cookie.CookieHelper;
import com.operational.platform.spider.bean.CrawlPointAttr;
import com.operational.platform.spider.component.handler.response.ResponseHandler;
import com.operational.platform.spider.exception.ElementNotExistException;
import com.operational.platform.spider.util.cookie.FilterCookies;
import com.operational.platform.spider.util.cookie.HttpCookieEx;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private Map<String, String> paramMap = new HashMap<>();

    /**
     * 初始化
     *
     * @param crawlPointAttr
     * @throws UnsupportedEncodingException
     */
    public void init(CrawlPointAttr crawlPointAttr) throws UnsupportedEncodingException, URISyntaxException {
        this.crawlPointAttr = crawlPointAttr;
        if ("POST".equalsIgnoreCase(crawlPointAttr.getMethod())) {
            httpPost = new HttpPost(crawlPointAttr.getUrl());

            //设置post参数
            String postParam = crawlPointAttr.getPostParam();
            if (!StringUtils.isEmpty(postParam)) {
                String[] kvs = postParam.split("&");
                for (String kv : kvs) {
                    String[] strs = kv.split("=");
                    paramMap.put(strs[0].trim(), strs[1].trim());
                }
            }

            /*String postParam = crawlPointAttr.getPostParam();
            if (!StringUtils.isEmpty(postParam)) {
                List<NameValuePair> params = new ArrayList<>();
                String[] kvs = postParam.split("&");
                for (String kv : kvs) {
                    String[] strs = kv.split("=");
                    params.add(new BasicNameValuePair(strs[0].trim(), strs[1].trim()));
                }
                httpPost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
            }*/

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
        if (!StringUtils.isEmpty(crawlPointAttr.getCookies())) {
            List<HttpCookieEx> cookieList = new ArrayList<>();
            cookieList.addAll(FilterCookies.filter(crawlPointAttr.getCookies()));
            CookieHelper.setCookies2(crawlPointAttr.getUrl(), httpUriRequest, cookieList);
        }
        if (!StringUtils.isEmpty(crawlPointAttr.getHeader())) {
            String[] kvs = crawlPointAttr.getHeader().split(";");
            for (String kv : kvs) {
                String[] strs = kv.split(":");
                httpUriRequest.setHeader(strs[0].trim(), strs[1].trim());
            }
        }
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
    public String next() throws URISyntaxException, RequestException, ElementNotExistException, ClassNotFoundException, IllegalAccessException, InstantiationException, UnsupportedEncodingException {

        if ("POST".equalsIgnoreCase(crawlPointAttr.getMethod())) {// POST请求
            curCount++;
            List<NameValuePair> params = new ArrayList<>();
            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                if (entry.getKey().startsWith("$")) {
                    params.add(new BasicNameValuePair(entry.getKey().replaceFirst("\\$", ""), curCount.toString()));
                } else {
                    params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
            }
            httpPost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
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

        //处理response
        if (!StringUtils.isEmpty(crawlPointAttr.getResponseHandler())) {
            ResponseHandler responseHandler = (ResponseHandler) Class.forName(crawlPointAttr.getResponseHandler())
                    .newInstance();
            response = responseHandler.handle(response);
        }

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

    public Integer getCurCount() {
        return curCount;
    }
}
