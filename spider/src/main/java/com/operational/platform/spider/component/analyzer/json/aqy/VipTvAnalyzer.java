package com.operational.platform.spider.component.analyzer.json.aqy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.operational.platform.spider.bean.ParseResult;
import com.operational.platform.spider.component.JsonAnalyzer;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lihuajun on 2017/7/23.
 */
public class VipTvAnalyzer extends JsonAnalyzer {
    @Override
    public List<ParseResult> parse(String jsonResponse) {

        List<ParseResult> list = new ArrayList<>();
        JSONArray docs = null;
        try {
            if (JSONObject.parseObject(jsonResponse).getString("data").contains("search result is empty"))
                return list;
            docs = JSONObject.parseObject(jsonResponse).getJSONObject("data").getJSONArray("docinfos");
        } catch (Exception e) {
            LOGGER.error("解析列表发生错误[{}]", jsonResponse, e);
            return list;
        }
        for (int i = 0; docs != null && i < docs.size(); i++) {
            try {
                JSONObject obj = docs.getJSONObject(i).getJSONObject("albumDocInfo");
                ParseResult parseResult = new ParseResult();
                parseResult.setTitle(obj.getString("albumTitle"));
                String link = obj.getString("albumLink");
                if(StringUtils.isEmpty(link)){
                    link = obj.getJSONObject("video_lib_meta").getString("link");
                }
                //parseResult.setLink(link.replaceFirst("www.", "m.").replaceFirst("vip.", "m."));
                parseResult.setLink(link);
                Map<String, String> attr = new HashMap<>();
                attr.put("desc", obj.getJSONObject("video_lib_meta").getString("description"));

                ///////////////////////////////
                attr.put("star", obj.getString("star"));//明星
                attr.put("playCount", obj.getString("playCount"));//播放次数
                String[] sTags = obj.getString("threeCategory").replaceAll("[^(\\u4e00-\\u9fa5) \\|,]", "").split(",");
                List<String> tags = new ArrayList<>();
                for (int j = 0; j < sTags.length; j++) {
                    if (!StringUtils.isEmpty(sTags[j].trim())) {
                        tags.add(sTags[j].trim());
                    }
                }
                attr.put("tags", JSON.toJSONString(tags));//标签
                attr.put("tvFocus", obj.getString("tvFocus"));//看点
                JSONArray videoInfos = obj.getJSONArray("videoinfos");
                if (videoInfos != null && videoInfos.size() > 0) {
                    JSONObject videoInfo = videoInfos.getJSONObject(0);
                    attr.put("timeLength", videoInfo.getString("timeLength"));//时长
                    attr.put("tvid", videoInfo.getString("tvId"));//电视剧第一集
                }
                attr.put("directors", obj.getJSONObject("video_lib_meta").getString("director"));//导演
                attr.put("actors", obj.getJSONObject("video_lib_meta").getString("actor"));//演员
                attr.put("docid", obj.getString("albumId"));//docid
                //////////////////////////////

                String logo = obj.getString("albumVImage").replace(".jpg", "_195_260.jpg");
                CloseableHttpResponse httpResponse = null;
                CloseableHttpClient httpClient = HttpClients.custom()
                        .build();
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
                attr.put("score", obj.getString("score"));
                attr.put("releaseDate", obj.getString("releaseDate"));//发行日期
                attr.put("itemTotalNumber", obj.getString("itemTotalNumber"));//集数
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
            } catch (Exception e) {
                LOGGER.error("解析列表发生错误[{}]", docs.getJSONObject(i).toString(), e);
            }
        }

        return list;
    }
}
