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
        pageCount = 40;
    }
}
