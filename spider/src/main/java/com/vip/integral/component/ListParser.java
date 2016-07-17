package com.vip.integral.component;

import com.alibaba.fastjson.JSONObject;
import com.vip.integral.bean.CrawlPoint;
import com.vip.integral.bean.ParseResult;
import com.vip.integral.component.analyzer.JsonAnalyzer;
import com.vip.integral.constant.ExceptionTypeEnum;
import com.vip.integral.exception.FavourUrlParseException;
import com.vip.integral.exception.ListParamsParseException;
import com.vip.integral.exception.ListPositionParseException;
import com.vip.integral.util.JsHelper;
import com.vip.integral.util.StrUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 列表解析器
 *
 * @author: Zhou Xuanang
 * @Date: 9:40 16/6/24.
 */
@Service
@Scope("prototype")
public class ListParser {

    protected final static Logger LOGGER = LoggerFactory.getLogger(ListParser.class);

    @Autowired
    private JsonAnalyzerFactory jsonAnalyzerFactory;

    protected CrawlPoint crawlPoint;
    private String posRule;
    private String favourUrlRule;
    private String jsonAnalyzerPath;
    private String listRuleJson;
    private String selfRule;// 自定义方式提取链接的规则

    /**
     * 列表解析器入口
     *
     * @param content
     * @param responseType
     * @return
     */
    public List<ParseResult> parse(String content, String responseType)
            throws ListPositionParseException, ListParamsParseException, FavourUrlParseException {
        if (responseType.equals("HTML")) {
            return parseHtml(content);
        } else {
            return parseJson(content);
        }
    }

    /**
     * 解析Html返回类型
     *
     * @param content
     * @return
     */
    private List<ParseResult> parseHtml(String content)
            throws ListPositionParseException, ListParamsParseException, FavourUrlParseException {
        List<ParseResult> list = new ArrayList<>();

        if (StringUtils.isEmpty(listRuleJson))
            listRuleJson = "[]";
        List<ListParams> listParamsList = JSONObject.parseArray(listRuleJson, ListParams.class);

        // 列表位置解析
        Elements elements;
        Document doc = Jsoup.parse(content);
        try {
            elements = doc.select(posRule);
        } catch (Exception e) {
            throw new ListPositionParseException(ExceptionTypeEnum.LIST_POSITION_PARSE_ERROR);
        }

        // 列表参数解析
        for (Element element : elements) {
            ParseResult parseResult = new ParseResult();
            for (ListParams listParams : listParamsList) {
                try {
                    String value;
                    Element targetElement;

                    if (".".equals(listParams.getJsoup())) {// 如果规则为 ‘.’ 则代表当前节点
                        targetElement = element;
                    } else {
                        targetElement = element.select(listParams.getJsoup()).get(0);
                    }

                    if (StringUtils.isEmpty(listParams.getAttr())) {// 默认取text属性
                        value = targetElement.text().trim();
                    } else if ("html".equalsIgnoreCase(listParams.getAttr())) {
                        value = targetElement.outerHtml();
                    } else {
                        value = targetElement.attr(listParams.getAttr());
                    }

                    // 正则表达式过滤
                    if (!StringUtils.isEmpty(listParams.getRegex())) {
                        value = this.filterStr(value, listParams.getRegex());
                    }
                    parseResult.getAttr().put(listParams.getKey(), value);
                } catch (Exception e) {
                    throw new ListParamsParseException(ExceptionTypeEnum.LIST_PARAMS_PARSE_ERROR);
                }
            }

            // 优惠详情URL获取
            try {
                String link;
                if (isJson(favourUrlRule)) {// {'jsoup':'xx','attr':'xx'}
                    JSONObject jsonObject = JSONObject.parseObject(favourUrlRule);
                    link = element.select(jsonObject.getString("jsoup")).get(0).attr(jsonObject.getString("attr"));
                } else {
                    if (".".equals(favourUrlRule))
                        link = element.attr("href");
                    else
                        link = element.select(favourUrlRule).get(0).attr("href");
                }

                // 需要自定义方式提取规则
                if (!StringUtils.isEmpty(selfRule)) {// {'by':'js','method':'xxx','code':''}
                    JSONObject jsonObject = JSONObject.parseObject(selfRule);

                    if ("js".equalsIgnoreCase(jsonObject.getString("by"))) {// js方式
                        link = JsHelper.exe(jsonObject.getString("method"), link);
                    }
                }
                parseResult.setLink(StrUtil.cleanUrl(link));
            } catch (Exception e) {
                throw new FavourUrlParseException(ExceptionTypeEnum.FAVOUR_URL_PARSE_ERROR);
            }

            LOGGER.info("ParseResult:" + JSONObject.toJSONString(parseResult));

            list.add(parseResult);
        }

        return list;
    }

    /**
     * 解析Json返回类型
     *
     * @param content
     * @return
     */
    private List<ParseResult> parseJson(String content) {
        JsonAnalyzer jsonAnalyzer = jsonAnalyzerFactory.getJsonAnalyzer(jsonAnalyzerPath);
        jsonAnalyzer.crawlPoint = crawlPoint;
        return jsonAnalyzer.parse(content);
    }

    // 初始化
    public void init(CrawlPoint crawlPoint) {
        this.crawlPoint = crawlPoint;
        this.posRule = crawlPoint.getListPosRule();
        this.favourUrlRule = crawlPoint.getUrlRule();
        this.jsonAnalyzerPath = crawlPoint.getJsonAnalyzePath();
        this.listRuleJson = crawlPoint.getListRule();
        this.selfRule = crawlPoint.getUrlScript();
    }

    // 正则表达式过滤字符串
    private String filterStr(String expression, String regStr) {
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(expression);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            sb.append(matcher.group());
        }
        return sb.toString();
    }

    // 判断是否是JSON
    private boolean isJson(String str) {
        try {
            // TODO: 16-6-30 有更好的办法吗？
            JSONObject.parseObject(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}

class ListParams {
    private String key;
    private String jsoup;
    private String regex;
    private String attr;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {

        this.key = key;
    }

    public String getJsoup() {

        return jsoup;
    }

    public void setJsoup(String jsoup) {

        this.jsoup = jsoup;
    }

    public String getRegex() {

        return regex;
    }

    public void setRegex(String regex) {

        this.regex = regex;
    }

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }
}

