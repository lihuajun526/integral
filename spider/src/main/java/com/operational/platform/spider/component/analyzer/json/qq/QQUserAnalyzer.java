package com.operational.platform.spider.component.analyzer.json.qq;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.operational.platform.spider.bean.ParseResult;
import com.operational.platform.spider.component.JsonAnalyzer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lihuajun on 2017/7/23.
 */
public class QQUserAnalyzer extends JsonAnalyzer {

    private static final Logger LOGGER = LoggerFactory.getLogger(QQUserAnalyzer.class);

    @Override
    public List<ParseResult> parse(String jsonResponse) {

        List<ParseResult> list = new ArrayList<>();
        JSONObject result = JSONObject.parseObject(jsonResponse);
        if (result.getInteger("retcode").intValue() != 0)
            return list;
        JSONArray datas = result.getJSONObject("result").getJSONObject("buddy").getJSONArray("info_list");
        for (int i = 0; datas != null && i < datas.size(); i++) {
            ParseResult parseResult = new ParseResult();
            JSONObject data = datas.getJSONObject(i);
            parseResult.setLink(data.getString("uin"));
            try {
                parseResult.setTitle(URLEncoder.encode(data.getString("nick"), "utf-8"));
            } catch (UnsupportedEncodingException e) {
                LOGGER.error("编码{}失败", data.getString("nick"));
                continue;
            }
            Map<String, String> attr = new HashMap<>();
            attr.put("face", data.getString("face"));
            attr.put("distance", data.getString("distance"));
            attr.put("age", data.getString("age"));
            attr.put("province", data.getString("province"));
            attr.put("gender", data.getString("gender"));
            attr.put("headimg", data.getString("url"));
            attr.put("stat", data.getString("stat"));
            attr.put("country", data.getString("country"));
            attr.put("city", data.getString("city"));
            parseResult.setAttr(attr);

            list.add(parseResult);
        }
        return list;
    }
}
