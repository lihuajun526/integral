package com.operational.platform.taskbreak.component;

import com.alibaba.fastjson.JSON;
import com.operational.platform.common.util.XHttpClient;
import com.operational.platform.taskbreak.util.StrUtils;
import com.operational.platform.taskbreak.entity.BreakRule;
import org.apache.http.client.methods.HttpGet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: Zhou Xuanang
 * @Date: 09:52 2016/11/3.
 */
public class RequestAndParse implements Callable<Object> {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestAndParse.class);

    private String url;
    private BreakRule breakRule;
    private String countRuleJson;

    public RequestAndParse(String url, String countRuleJson, BreakRule breakRule) {
        this.url = url;
        this.countRuleJson = countRuleJson;
        this.breakRule = breakRule;
    }

    @Override
    public Object call() throws Exception {
        List<String> resultUrlList = new ArrayList<>();

        try {
            String response = XHttpClient.doRequest(new HttpGet(url));

            Document doc = Jsoup.parse(response);
            //
            Elements elements = new Elements();
            Elements numElement = doc.select(JSON.parseObject(countRuleJson).get("jsoup").toString());
            if (numElement.size() == 1) {
                Pattern p = Pattern.compile(JSON.parseObject(countRuleJson).get("regex").toString());
                Matcher m = p.matcher(numElement.get(0).text());
                StringBuffer sb = new StringBuffer();
                if (m.find()) {
                    sb.append(m.group(1));
                    if (Integer.parseInt(sb.toString()) <= Integer.parseInt(JSON.parseObject(countRuleJson).get("recordLimit")
                            .toString())) {
                        return url;
                    }
                }
            }

            if (breakRule.getJsoup().contains(",")) {
                String[] ruleArray = breakRule.getJsoup().split(",");
                for (String oneRule : ruleArray) {
                    elements.addAll(doc.select(oneRule));
                }
            } else {
                elements.addAll(doc.select(breakRule.getJsoup()));
            }

            for (Element element : elements) {
                if (!StringUtils.isEmpty(breakRule.getRegex())) {
                    Pattern p = Pattern.compile(breakRule.getRegex());
                    Matcher m = p.matcher(StrUtils.handleLink(url, element.attr(breakRule.getAttr())));

                    StringBuffer sb = new StringBuffer();
                    if (m.find()) {
                        sb.append(m.group(1));
                        String resultUrl = sb.toString();
                        resultUrlList.add(resultUrl);
                    }
                } else {
                    String resultUrl = StrUtils.handleLink(url, element.attr(breakRule.getAttr()));
                    resultUrlList.add(resultUrl);
                }
            }
        } catch (Exception e) {
            LOGGER.info("error:", e);
        }

        return resultUrlList;
    }
}
