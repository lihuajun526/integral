package com.operational.platform.spider.component.analyzer.json.aqy;

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
public class VipMovieAnalyzer extends JsonAnalyzer {
    @Override
    public List<ParseResult> parse(String jsonResponse) {

        List<ParseResult> list = new ArrayList<>();
        JSONArray docs = JSONObject.parseObject(jsonResponse).getJSONObject("data").getJSONArray("docinfos");
        for (int i = 0; i < docs.size(); i++) {
            JSONObject obj = docs.getJSONObject(i).getJSONObject("albumDocInfo");
            ParseResult parseResult = new ParseResult();
            parseResult.setTitle(obj.getString("albumTitle"));
            parseResult.setLink(obj.getString("albumLink"));
            Map<String, String> attr = new HashMap<>();
            attr.put("desc", obj.getJSONObject("video_lib_meta").getString("description"));
            attr.put("logo", obj.getString("albumVImage").replace(".jpg", "_195_260.jpg"));
            attr.put("score", obj.getString("score"));
            attr.put("threeCategory", obj.getString("threeCategory"));
            parseResult.setAttr(attr);
            list.add(parseResult);
        }

        return list;
    }
}
