package com.operational.platform.spider.component.loader.index.aqy;

import com.operational.platform.spider.component.loader.PageIndexLoader;
import com.operational.platform.spider.constant.ExceptionTypeEnum;
import com.operational.platform.spider.exception.ElementNotExistException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 电影
 * Created by lihuajun on 16-7-29.
 */
public class Dy extends PageIndexLoader {

    @Override public void updatePageCount(String response) throws ElementNotExistException {
        try {
            Document doc = Jsoup.parse(response);

            Elements elements = doc.select("div.mod-page");

            if (elements != null && elements.size() > 0) {
                Element element = elements.get(0);
                if (element.children().size() > 0) {
                    elements = doc.select(crawlPointAttr.getPageIndexRule());
                    if (elements == null || elements.size() == 0) {
                        pageCount++;
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("更新页数错误[采集点id={},category={}]:", crawlPointAttr.getId(), crawlPointAttr.getCategory(), e);
            throw new ElementNotExistException(ExceptionTypeEnum.ELEMENT_NOT_EXIST_ERROR.code,
                    "无法获取[爱奇艺->" + crawlPointAttr.getCategory() + "]分页导航");
        }
    }
}
