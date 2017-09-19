package com.operational.platform.spider.component.list.impl;

import com.operational.platform.spider.bean.ParseResult;
import com.operational.platform.spider.component.list.ListHandler;
import com.operational.platform.spider.constant.ExceptionTypeEnum;
import com.operational.platform.spider.exception.ListRecordParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihuajun on 2017/9/19.
 */
public class Smt implements ListHandler {
    private final static Logger LOGGER = LoggerFactory.getLogger(ListHandler.class);

    @Override
    public List<ParseResult> handle(String content, List<ParseResult> list) throws ListRecordParseException {


        if (list == null || list.size() == 0)
            return list;

        // 列表位置解析
        Elements elements;
        Document doc = Jsoup.parse(content);
        try {
            elements = doc.select("div.leftTableDivID>table.table>tbody>tr>td");
        } catch (Exception e) {
            LOGGER.error("获取列表记录错误", e);
            throw new ListRecordParseException(ExceptionTypeEnum.LIST_POSITION_PARSE_ERROR);
        }

        List<ParseResult> l = new ArrayList<>();
        // 列表参数解析，解析标题和详细页链接
        for (Element element : elements) {
            ParseResult parseResult = new ParseResult();

            if (element.select("a") == null || element.select("a").size() == 0) {
                parseResult.setTitle(null);
                l.add(parseResult);
                continue;
            }
            element = element.select("a").get(0);
            parseResult.setTitle(element.text());
            parseResult.setLink(element.attr("href"));
            l.add(parseResult);
        }

        if (l.size() == 0) {
            LOGGER.error("解析出错，结果为空");
            throw new ListRecordParseException(ExceptionTypeEnum.LIST_POSITION_PARSE_ERROR);
        }

        list = list.subList(1, list.size());
        List<ParseResult> newList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ParseResult parseResult = list.get(i);
            ParseResult p = l.get(i);

            if (p.getTitle() == null)
                continue;

            parseResult.setTitle(p.getTitle());
            parseResult.setLink("http://pe.pedata.cn/" + p.getLink());

            newList.add(parseResult);
        }

        return newList;
    }
}
