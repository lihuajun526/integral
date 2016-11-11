package com.vip.taskbreak.component;

import com.alibaba.fastjson.JSON;
import com.vip.common.util.XHttpClient;
import com.vip.dbservice.model.CCrawlPoint;
import org.apache.http.client.methods.HttpGet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: Zhou Xuanang
 * @Date: 09:43 2016/11/7.
 */
public class ListPageRequestAndParse implements Callable<Object> {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestAndParse.class);

    private String url;
    private String countRuleJson;
    private String pageIndexRule;

    public ListPageRequestAndParse(String url, String countRuleJson, String pageIndexRule) {
        this.url = url;
        this.countRuleJson = countRuleJson;
        this.pageIndexRule = pageIndexRule;
    }

    @Override
    public Object call() throws Exception {
        CCrawlPoint cCrawlPoint = new CCrawlPoint();
        cCrawlPoint.setUrl(url);

        try {
            String response = XHttpClient.doRequest(new HttpGet(url));
            Document doc = Jsoup.parse(response);

            /***** 获取页数 *****/
            Elements elements = doc.select(pageIndexRule);
            if (elements == null || elements.size() == 0) {
                cCrawlPoint.setPageCount(1);
            } else {
                cCrawlPoint.setPageCount(Integer.parseInt(elements.get(elements.size() - 2).text()));
            }

            /***** 获取记录数 *****/
            Element element = doc.select(JSON.parseObject(countRuleJson).get("jsoup").toString()).get(0);
            if (StringUtils.isEmpty(JSON.parseObject(countRuleJson).get("attr").toString())) {
                Pattern p = Pattern.compile(JSON.parseObject(countRuleJson).get("regex").toString());
                Matcher m = p.matcher(element.text());
                if (m.find()) {
                    cCrawlPoint.setRecordCount(Integer.parseInt(m.group(0)));
                }
            } else {
                Pattern p = Pattern.compile(JSON.parseObject(countRuleJson).get("regex").toString());
                Matcher m = p.matcher(element.attr(JSON.parseObject(countRuleJson).get("attr").toString()));
                if (m.find()) {
                    cCrawlPoint.setRecordCount(Integer.parseInt(m.group(0)));
                }
            }
        } catch (Exception e) {
            LOGGER.error("error", e);
        }
        return cCrawlPoint;
    }
}
