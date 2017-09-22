package com.operational.platform.spider.component.loader.index.smt;

import com.operational.platform.spider.component.loader.PageIndexLoader;
import com.operational.platform.spider.constant.ExceptionTypeEnum;
import com.operational.platform.spider.exception.ElementNotExistException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Tzr extends PageIndexLoader {

    @Override
    public void updatePageCount(String response) throws ElementNotExistException {
        try {
            if (curCount == 1) {// 第一个页面就可以确定总页数
                Document doc = Jsoup.parse(response);
                Elements elements = doc.select(crawlPointAttr.getPageIndexRule());
                String sCount = elements.get(elements.size() - 2).text();
                pageCount = Integer.valueOf(sCount.trim());
            }
        } catch (Exception e) {
            LOGGER.error("在[{}]页更新总页数失败，规则为[{}]", curCount, crawlPointAttr.getPageIndexRule(), e);
            throw new ElementNotExistException(ExceptionTypeEnum.ELEMENT_NOT_EXIST_ERROR.code, ExceptionTypeEnum.ELEMENT_NOT_EXIST_ERROR.description);
        }

    }
}
