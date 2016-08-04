package com.vip.integral.attack.tengxun.analyzer;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.vip.integral.attack.tengxun.bean.TxItem;
import com.vip.integral.bean.ParseResult;
import com.vip.integral.component.JsonAnalyzer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 腾讯新闻Json解析类
 */
@Service
public class CommonAnalyzer extends JsonAnalyzer {

    @Override
    public List<ParseResult> parse(String jsonResponse) {

        List<ParseResult> list = new ArrayList<>();

        TxItem txItem = JSONObject.parseObject(jsonResponse, TxItem.class);

        for (int i = 0; i < txItem.getData().size(); i++) {
            TxItem.Data data = txItem.getData().get(i);
            ParseResult parseResult = new ParseResult();
            parseResult.setLink(data.getUrl());
            parseResult.setTitle(data.getTitle());
            list.add(parseResult);
        }

        LOGGER.info(JSONArray.toJSONString(list));

        return list;
    }
}
