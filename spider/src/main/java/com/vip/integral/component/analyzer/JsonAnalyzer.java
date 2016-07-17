package com.vip.integral.component.analyzer;


import com.vip.integral.bean.ParseResult;
import com.vip.integral.component.ListParser;

import java.util.List;

/**
 * @author: Zhou Xuanang
 * @Date: 13:44 16/6/24.
 */
public abstract class JsonAnalyzer extends ListParser {
    public abstract List<ParseResult> parse(String jsonResponse);
}
