package com.operational.platform.spider.component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.operational.platform.spider.bean.ParseResult;
import com.operational.platform.spider.exception.ListParamsParseException;
import com.operational.platform.spider.util.JsHelper;
import com.operational.platform.spider.util.StrUtil;
import com.operational.platform.spider.bean.CrawlPointAttr;
import com.operational.platform.spider.constant.ExceptionTypeEnum;
import com.operational.platform.spider.exception.FavourUrlParseException;
import com.operational.platform.spider.exception.ListRecordParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
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
@Component
@Scope("prototype")
public class ListParser {

    protected final static Logger LOGGER = LoggerFactory.getLogger(ListParser.class);

    protected CrawlPointAttr crawlPointAttr;
    private String listAttrRule;
    private String listRecordRule;
    private String linkRule;
    private String linkSelfRule;

    /**
     * @param content
     * @return
     * @throws ListRecordParseException
     * @throws ListParamsParseException
     * @throws FavourUrlParseException
     */
    public List<ParseResult> parse(String content)
            throws ListRecordParseException, ListParamsParseException, FavourUrlParseException, IllegalAccessException, InstantiationException, ClassNotFoundException {

        String responseType = crawlPointAttr.getResponseType();

        if (responseType.equals("HTML")) {
            return parseHtml(content);
        } else if (responseType.equals("JSON")) {
            return parseJson(content);
        } else if (responseType.equals("XML")) {
            LOGGER.info("http请求返回xml数据");
            return null;
        }
        return null;
    }

    /**
     * 解析Html返回类型
     *
     * @param content
     * @return
     */
    private List<ParseResult> parseHtml(String content)
            throws ListRecordParseException, ListParamsParseException, FavourUrlParseException {

        List<ParseResult> list = new ArrayList<>();

        if (StringUtils.isEmpty(listAttrRule)) {
            LOGGER.warn("id={}的采集点列表属性规则为空", crawlPointAttr.getId());
            return list;
        }

        List<ListParam> listParamsList = JSONObject.parseArray(listAttrRule, ListParam.class);
        // 列表位置解析
        Elements elements;
        Document doc = Jsoup.parse(content);
        try {
            elements = doc.select(listRecordRule);
        } catch (Exception e) {
            LOGGER.error("获取列表记录错误[id={},规则={}]:", crawlPointAttr.getId(), listRecordRule, e);
            throw new ListRecordParseException(ExceptionTypeEnum.LIST_POSITION_PARSE_ERROR);
        }
        // 列表参数解析
        for (Element element : elements) {
            ParseResult parseResult = new ParseResult();
            //列表属性解析
            for (ListParam listParam : listParamsList) {
                try {
                    String value;
                    Element targetElement;
                    if (".".equals(listParam.getJsoup())) {// 如果规则为 ‘.’ 则代表当前节点
                        targetElement = element;
                    } else {
                        if (element.select(listParam.getJsoup()).size() == 0)
                            continue;
                        targetElement = element.select(listParam.getJsoup()).get(0);
                    }
                    if (StringUtils.isEmpty(listParam.getAttr())) {// 默认取text属性
                        value = targetElement.text().trim();
                    } else if ("html".equalsIgnoreCase(listParam.getAttr())) {
                        value = targetElement.outerHtml();
                    } else {
                        value = targetElement.attr(listParam.getAttr());
                    }
                    // 正则表达式过滤
                    if (!StringUtils.isEmpty(listParam.getRegex())) {
                        value = this.filterStr(value, listParam.getRegex());
                    }
                    if (!StringUtils.isEmpty(listParam.getSelfRule())) {// {'by':'js','method':'xxx'}
                        JSONObject jsonObject = JSONObject.parseObject(listParam.getSelfRule());
                        if ("js".equalsIgnoreCase(jsonObject.getString("by"))) {// js方式
                            value = JsHelper.exe(jsonObject.getString("method"), value);
                        }
                    }
                    if ("title".equalsIgnoreCase(listParam.getKey())) {
                        parseResult.setTitle(value);
                    } else {
                        parseResult.getAttr().put(listParam.getKey(), value);
                    }
                } catch (Exception e) {
                    LOGGER.info(element.toString());
                    LOGGER.error("解析列表属性错误[id={},key={}]:", crawlPointAttr.getId(), JSON.toJSONString(listParam), e);
                    throw new ListParamsParseException(ExceptionTypeEnum.LIST_PARAMS_PARSE_ERROR);
                }
            }
            // link解析
            try {
                String link = null;

                LinkParam linkParam = JSONObject.parseObject(linkRule, LinkParam.class);
                if (".".equals(linkParam.getJsoup())) {
                    link = element.attr("href");
                } else {
                    link = element.select(linkParam.getJsoup()).get(0).attr("href");
                }

                //格式化链接
                if (!StringUtils.isEmpty(linkParam.getFormat())) {
                    link = String.format(linkParam.getFormat(), link);
                }
                // 需要自定义方式提取规则
                if (!StringUtils.isEmpty(linkSelfRule)) {// {'by':'js','method':'xxx'}
                    JSONObject jsonObject = JSONObject.parseObject(linkSelfRule);
                    if ("js".equalsIgnoreCase(jsonObject.getString("by"))) {// js方式
                        link = JsHelper.exe(jsonObject.getString("method"), link);
                    }
                }

                parseResult.setLink(StrUtil.handleLink(crawlPointAttr.getUrl(), StrUtil.cleanUrl(link)));
            } catch (Exception e) {
                LOGGER.error("解析链接错误[id={}]", crawlPointAttr.getId(), e);
                throw new FavourUrlParseException(ExceptionTypeEnum.FAVOUR_URL_PARSE_ERROR);
            }

            LOGGER.debug("ParseResult:" + JSONObject.toJSONString(parseResult));

            list.add(parseResult);
        }

        return list;
    }

    /**
     * 解析Json返回类型
     *
     * @param jsonResponse
     * @return
     */
    private List<ParseResult> parseJson(String jsonResponse) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        JsonAnalyzer jsonAnalyzer = (JsonAnalyzer) Class.forName(crawlPointAttr.getJsonAnalyzePath())
                .newInstance();
        return jsonAnalyzer.parse(jsonResponse);
    }

    // 初始化
    public void init(CrawlPointAttr crawlPointAttr) {
        this.crawlPointAttr = crawlPointAttr;
        this.listRecordRule = crawlPointAttr.getListRecordRule();
        this.listAttrRule = crawlPointAttr.getListAttrRule();
        this.linkRule = crawlPointAttr.getLinkRule();
        this.linkSelfRule = crawlPointAttr.getLinkSelfRule();
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

}

//link
class LinkParam {
    private String jsoup;
    private String attr;
    private String format;

    public String getJsoup() {
        return jsoup;
    }

    public void setJsoup(String jsoup) {
        this.jsoup = jsoup;
    }

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
}

//列表参数
class ListParam {
    private String key;
    private String jsoup;
    private String regex;
    private String attr;
    private String selfRule;

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

    public String getSelfRule() {
        return selfRule;
    }

    public void setSelfRule(String selfRule) {
        this.selfRule = selfRule;
    }
}

