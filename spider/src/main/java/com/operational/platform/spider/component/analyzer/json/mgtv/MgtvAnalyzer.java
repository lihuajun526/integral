package com.operational.platform.spider.component.analyzer.json.mgtv;

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
public class MgtvAnalyzer extends JsonAnalyzer {
    @Override
    public List<ParseResult> parse(String jsonResponse) {

        List<ParseResult> list = new ArrayList<>();
        JSONArray rows = JSONObject.parseObject(jsonResponse).getJSONObject("data").getJSONArray("rows");
        for (int i = 0; i < rows.size(); i++) {
            JSONArray datas = rows.getJSONObject(i).getJSONArray("moduleData");
            for (int j = 0; j < datas.size(); j++) {
                JSONObject data = datas.getJSONObject(j);
                ParseResult parseResult = new ParseResult();
                parseResult.setTitle(data.getString("name"));
                parseResult.setLink("http://m.mgtv.com/#/b/" + data.getString("jumpId") + "/" + data.getString("childId"));
                Map<String, String> attr = new HashMap<>();
                attr.put("desc", data.getString("subName"));
                attr.put("logo", data.getString("imgHVUrl"));
                parseResult.setAttr(attr);
                list.add(parseResult);
            }
        }

        return list;
    }
}
