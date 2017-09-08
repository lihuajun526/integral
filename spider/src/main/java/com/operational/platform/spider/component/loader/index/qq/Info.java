package com.operational.platform.spider.component.loader.index.qq;

import com.operational.platform.spider.component.loader.PageIndexLoader;
import com.operational.platform.spider.constant.ExceptionTypeEnum;
import com.operational.platform.spider.exception.ElementNotExistException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Info extends PageIndexLoader {

    @Override
    public void updatePageCount(String response) throws ElementNotExistException {
        if (curCount == 1) {
            pageCount = Integer.MAX_VALUE;
        }
    }
}
