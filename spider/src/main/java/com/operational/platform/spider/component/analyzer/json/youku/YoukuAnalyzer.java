package com.operational.platform.spider.component.analyzer.json.youku;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.operational.platform.spider.bean.ParseResult;
import com.operational.platform.spider.component.JsonAnalyzer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lihuajun on 2017/7/23.
 */
public class YoukuAnalyzer extends JsonAnalyzer {
    @Override
    public List<ParseResult> parse(String jsonResponse) {

        List<ParseResult> list = new ArrayList<>();
        JSONArray jsonArray = JSONObject.parseArray(jsonResponse);

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if (jsonObject.getIntValue("fee_type") == 2)//会员用券
                continue;
            ParseResult parseResult = new ParseResult();
            parseResult.setTitle(jsonObject.getString("showname"));
            parseResult.setLink(jsonObject.getString("show_vurl"));
            Map<String, String> attr = new HashMap<>();
            attr.put("desc", jsonObject.getString("showsubtitle"));
            attr.put("score", jsonObject.getString("reputation"));
            attr.put("logo", jsonObject.getString("thumburl"));
            parseResult.setAttr(attr);

            list.add(parseResult);
        }

        return list;
    }
}
