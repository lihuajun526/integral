package com.operational.platform.spider.component;


import com.operational.platform.spider.bean.ParseResult;

import java.util.List;

/**
 * @author: Zhou Xuanang
 * @Date: 13:44 16/6/24.
 */
public abstract class JsonAnalyzer extends ListParser {
    public abstract List<ParseResult> parse(String jsonResponse);
}
