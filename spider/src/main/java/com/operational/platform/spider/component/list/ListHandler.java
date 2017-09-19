package com.operational.platform.spider.component.list;

import com.operational.platform.spider.bean.ParseResult;
import com.operational.platform.spider.exception.ListRecordParseException;

import java.util.List;

/**
 * Created by lihuajun on 2017/9/19.
 */
public interface ListHandler {

    List<ParseResult> handle(String content, List<ParseResult> list) throws ListRecordParseException;

}
