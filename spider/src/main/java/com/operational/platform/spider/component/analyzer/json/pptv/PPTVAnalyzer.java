package com.operational.platform.spider.component.analyzer.json.pptv;

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
public class PPTVAnalyzer extends JsonAnalyzer {
    @Override
    public List<ParseResult> parse(String jsonResponse) {

        List<ParseResult> list = new ArrayList<>();
        JSONArray datas = JSONObject.parseObject(jsonResponse).getJSONObject("result").getJSONArray("data");
        for (int i = 0; i < datas.size(); i++) {
            JSONObject jsonObject = datas.getJSONObject(i);
            JSONObject c = jsonObject.getJSONObject("cornericon_new");
            if(!c.getString("txt").equals("VIP免费"))
                continue;
            ParseResult parseResult = new ParseResult();
            parseResult.setTitle(jsonObject.getString("title"));
            parseResult.setLink(jsonObject.getString("link"));
            Map<String, String> attr = new HashMap<>();
            attr.put("desc", jsonObject.getString("subTitle"));
            attr.put("score", jsonObject.getString("mark"));
            attr.put("logo", jsonObject.getString("img"));
            parseResult.setAttr(attr);

            list.add(parseResult);
        }

        return list;
    }
}
