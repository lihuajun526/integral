package com.operational.platform.spider.component.loader.index.zlzp;

import com.operational.platform.spider.component.loader.PageIndexLoader;
import com.operational.platform.spider.constant.ExceptionTypeEnum;
import com.operational.platform.spider.exception.ElementNotExistException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Jl extends PageIndexLoader {

    @Override public void updatePageCount(String response) throws ElementNotExistException {
        try {
            if (curCount == 1) {
                Document doc = Jsoup.parse(response);
                Elements elements = doc.select(crawlPointAttr.getPageIndexRule());
                String text = elements.get(0).text();
                pageCount = Integer.valueOf(text.split("/")[1]);
            }
        } catch (Exception e) {
            LOGGER.error("更新页数错误[采集点id={},category={}]:", crawlPointAttr.getId(), crawlPointAttr.getCategory(), e);
            throw new ElementNotExistException(ExceptionTypeEnum.ELEMENT_NOT_EXIST_ERROR.code,
                    "无法获取[智联招聘->简历]分页导航");
        }
    }
}
