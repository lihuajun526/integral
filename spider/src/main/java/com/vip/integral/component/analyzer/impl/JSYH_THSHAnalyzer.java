package com.vip.integral.component.analyzer.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.vip.integral.bean.ParseResult;
import com.vip.integral.component.analyzer.JsonAnalyzer;
import com.vip.integral.constant.ListParamsKey;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 建设银行Json解析类
 * 
 * @author: Zhou Xuanang
 * @Date: 10:00 16/6/30.
 */
@Service
public class JSYH_THSHAnalyzer extends JsonAnalyzer {

    private final String URL_TEMPLATE = "http://creditcard.ccb.com/cn/creditcard/favorable/%s.html";

    @Override
    public List<ParseResult> parse(String jsonResponse) {
        List<ParseResult> list = new ArrayList<>();

        JSONObject jsonObject = JSONObject.parseObject(jsonResponse);
        JSONArray jsonArray = (JSONArray) jsonObject.get("obj");

        for (Object object : jsonArray) {
            ParseResult parseResult = new ParseResult();

            parseResult.setLink(String.format(URL_TEMPLATE, ((JSONObject) object).getString("biz_id")));
            parseResult.getAttr().put(ListParamsKey.TITLE, ((JSONObject) object).getString("biz_name"));
            parseResult.getAttr().put(ListParamsKey.CITY, ((JSONObject) object).getString("city"));
            parseResult.getAttr().put(ListParamsKey.ADDRESS, ((JSONObject) object).getString("biz_addr"));
            parseResult.getAttr().put(ListParamsKey.DESCRIPTION, ((JSONObject) object).getString("biz_desc"));

            list.add(parseResult);
            LOGGER.info(JSONObject.toJSONString(parseResult));
        }

        return list;
    }
}
