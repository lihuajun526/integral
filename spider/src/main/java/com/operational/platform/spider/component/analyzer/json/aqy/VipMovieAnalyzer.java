package com.operational.platform.spider.component.analyzer.json.aqy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.operational.platform.spider.bean.ParseResult;
import com.operational.platform.spider.component.JsonAnalyzer;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
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
            String logo = obj.getString("albumVImage").replace(".jpg", "_195_260.jpg");
            CloseableHttpResponse httpResponse = null;
            CloseableHttpClient httpClient = HttpClients.custom()
                    .build();
            try {
                httpResponse = httpClient.execute(new HttpGet(logo));
                int statusCode = httpResponse.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    attr.put("logo", logo);
                } else {
                    logo = logo.replace("_195_260.jpg", ".jpg");
                    httpResponse = httpClient.execute(new HttpGet(logo));
                    statusCode = httpResponse.getStatusLine().getStatusCode();
                    if (statusCode == 200) {
                        attr.put("logo", logo);
                    } else {
                        LOGGER.error("logo=[{}]找不到");
                    }
                }
            } catch (IOException e) {
                LOGGER.error("error:", e);
            }

            attr.put("score", obj.getString("score"));
            //attr.put("threeCategory", obj.getString("threeCategory"));
            attr.put("releaseDate", obj.getString("releaseDate"));//发行日期
            Boolean isPay = obj.getBoolean("on_demand");//付费/用券
            if (isPay != null && isPay) {
                attr.put("isPay", isPay.toString());
            }
            Boolean isExclusive = obj.getBoolean("is_exclusive");//是否独播
            if (isExclusive != null && isExclusive) {
                attr.put("isExclusive", isExclusive.toString());
            }
            Boolean homeMade = obj.getBoolean("is_qiyi_produced");//是否自制
            if (homeMade != null && homeMade) {
                attr.put("homeMade", homeMade.toString());
            }
            parseResult.setAttr(attr);
            list.add(parseResult);
        }

        return list;
    }
}
