package com.operational.platform.spider.component.loader;

import com.alibaba.fastjson.JSONObject;
import com.operational.platform.common.util.XHttpClient;
import com.operational.platform.spider.bean.CrawlPointAttr;
import com.operational.platform.spider.bean.PageRule;
import com.operational.platform.spider.bean.PageRuleKV;
import com.operational.platform.spider.bean.ParseResult;
import com.operational.platform.spider.component.Downloader;
import com.operational.platform.spider.constant.ListParamsKey;
import com.operational.platform.spider.exception.RequestException;
import com.operational.platform.spider.util.StrUtil;
import com.operational.platform.spider.util.cookie.CookieHelper;
import com.operational.platform.spider.util.cookie.FilterCookies;
import com.operational.platform.spider.util.cookie.HttpCookieEx;
import org.apache.http.client.methods.HttpGet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lihuajun on 16-7-17.
 */
public class PageLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(PageLoader.class);

    private CrawlPointAttr crawlPointAttr;
    private HttpGet httpGet;

    public void init(CrawlPointAttr crawlPointAttr) throws URISyntaxException {
        this.crawlPointAttr = crawlPointAttr;
        httpGet = new HttpGet();
        // 设置请求Header
        if (!StringUtils.isEmpty(crawlPointAttr.getAccept()))
            httpGet.setHeader("Accept", crawlPointAttr.getAccept());
        if (!StringUtils.isEmpty(crawlPointAttr.getReferer()))
            httpGet.setHeader("Referer", crawlPointAttr.getReferer());
        if (!StringUtils.isEmpty(crawlPointAttr.getCookies())) {
            List<HttpCookieEx> cookieList = new ArrayList<>();
            cookieList.addAll(FilterCookies.filter(crawlPointAttr.getCookies()));
            CookieHelper.setCookies2(crawlPointAttr.getUrl(), httpGet, cookieList);
        }
        if (!StringUtils.isEmpty(crawlPointAttr.getHeader())) {
            String[] kvs = crawlPointAttr.getHeader().split("&");
            for (String kv : kvs) {
                String[] strs = kv.split(":");
                httpGet.setHeader(strs[0].trim(), strs[1].trim());
            }
        }
    }

    /**
     * 页面加载
     *
     * @param parseResult
     * @throws IOException
     */
    public void load(ParseResult parseResult) throws IOException, URISyntaxException, RequestException, com.operational.platform.common.exception.RequestException {
        PageRule pageRule = JSONObject.parseObject(crawlPointAttr.getPageRule(), PageRule.class);
        parse(parseResult, pageRule, parseResult.getLink());
    }

    /**
     * 递归解析
     *
     * @param parseResult
     * @param pageRule
     * @throws IOException
     */
    private void parse(ParseResult parseResult, PageRule pageRule, String nextLevelLink) throws IOException, URISyntaxException, RequestException, com.operational.platform.common.exception.RequestException {

        if (StringUtils.isEmpty(parseResult.getLink()))
            return;

        // 请求当前页面
        httpGet.setURI(new URI(nextLevelLink));
        String response = XHttpClient.doRequest(httpGet, crawlPointAttr.getSleepTime());
        Document doc = Jsoup.parse(response);

        // 解析当前页面属性
        parseAttr(parseResult, doc, pageRule.getAttrs());
        // 解析当前页面进入下一页的链接集合
        if (pageRule.getNexts() == null)
            return;
        for (PageRuleKV next : pageRule.getNexts()) {
            List<String> nextLinkList = parseNextLink(parseResult, doc, next);
            nextLinkList = StrUtil.handleLink(parseResult.getLink(), nextLinkList);
            for (String nextLink : nextLinkList) {
                this.parse(parseResult, next.getChild(), nextLink);
            }
        }
    }

    // 解析页面属性
    private void parseAttr(ParseResult parseResult, Document doc, List<PageRuleKV> attrs)
            throws IOException {

        for (PageRuleKV attrRule : attrs) {
            String value = null;
            Element targetElement = null;

            if (".".equals(attrRule.getJsoup())) {
                targetElement = doc;
            } else {
                Elements elements = doc.select(attrRule.getJsoup());
                if (elements == null || elements.size() == 0)
                    continue;
                targetElement = elements.get(0);
            }
            // 获取属性默认取text属性
            if (StringUtils.isEmpty(attrRule.getAttr())) {
                value = targetElement.text().trim();
            } else if ("html".equalsIgnoreCase(attrRule.getAttr())) {
                value = targetElement.outerHtml();
            } else if ("inner".equalsIgnoreCase(attrRule.getAttr())) {
                value = targetElement.html();
            } else {
                value = targetElement.attr(attrRule.getAttr());
            }
            // 正则表达式过滤
            if (!StringUtils.isEmpty(attrRule.getRegex())) {
                List<String> strList = filterStr(value, attrRule.getRegex());
                value = strList.size() == 0 ? "" : strList.get(0);
            }
            if (ListParamsKey.PICTURE.equalsIgnoreCase(attrRule.getKey())) {
                if (value.indexOf("lookResumes.jpg") != -1) {
                    continue;
                }
                String picUrl = StrUtil.handleLink(parseResult.getLink(), value);
                if (!StringUtils.isEmpty(picUrl)) {
                    value = Downloader.downloadPic(picUrl);
                }
            }
            parseResult.getAttr().put(attrRule.getKey(), value);
        }
    }

    // 解析下一页链接
    private List<String> parseNextLink(ParseResult parseResult, Document doc, PageRuleKV nextRule) {

        List<String> nextLinkList = new ArrayList<>();

        String value = null;
        Elements elements = doc.select(nextRule.getPosRule());
        if (elements == null || elements.size() == 0) {
            return nextLinkList;
        }
        for (Element element : elements) {
            Element targetElement = null;
            if (StringUtils.isEmpty(nextRule.getJsoup()) || ".".equals(nextRule.getJsoup())) {
                targetElement = element;
            } else {
                targetElement = element.select(nextRule.getJsoup()).get(0);
            }
            // 通过jsoup获取下一层链接
            if (StringUtils.isEmpty(nextRule.getAttr())) {
                value = targetElement.attr("href");
            } else {
                value = targetElement.attr(nextRule.getAttr());
            }
            nextLinkList.add(value);
            // 正则表达式过滤
            if (!StringUtils.isEmpty(nextRule.getRegex())) {
                List<String> strList = filterStr(value, nextRule.getRegex());
                if (strList.size() > 0)
                    nextLinkList.add(strList.get(0));
            }
            // 通过正则表达式获取下一层链接
            if (!StringUtils.isEmpty(nextRule.getLinkRegex()))
                nextLinkList.addAll(filterStr(doc.outerHtml(), nextRule.getLinkRegex()));
        }

        return nextLinkList;
    }

    // 正则表达式过滤字符串
    private List<String> filterStr(String expression, String regStr) {
        List<String> strList = new ArrayList<>();
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(expression);

        while (matcher.find()) {
            strList.add(matcher.group());
        }
        return strList;
    }
}
