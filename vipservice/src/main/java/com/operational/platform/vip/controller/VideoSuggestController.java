package com.operational.platform.vip.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.operational.platform.common.exception.RequestException;
import com.operational.platform.common.util.XHttpClient;
import com.operational.platform.dbservice.model.Stage;
import com.operational.platform.dbservice.model.VideoSuggest;
import com.operational.platform.dbservice.service.StageService;
import com.operational.platform.dbservice.service.VideoSuggestService;
import com.operational.platform.vip.base.BaseController;
import com.operational.platform.vip.base.Result;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lihuajun on 16-7-6.
 */
@Controller
@RequestMapping("/video/suggest")
public class VideoSuggestController extends BaseController {

    @Autowired
    private VideoSuggestService videoSuggestService;

    @RequestMapping("/list/{channelid}/{pagesize}/{pageindex}")
    @ResponseBody
    public String list(@PathVariable Integer channelid, @PathVariable Integer pagesize, @PathVariable Integer pageindex) {

        Map<String, Object> data = new HashMap<>();
        Result<Map<String, Object>> result = new Result<>();

        List<VideoSuggest> list = videoSuggestService.listByChnlAndPage(channelid, pagesize, pageindex, null);
        long total = videoSuggestService.countByChnlAndPage(channelid, null);
        long pageCount = total % pagesize == 0 ? total / pagesize : (total / pagesize + 1);

        data.put("pageCount", pageCount);
        data.put("videos", list);

        result.setData(data);

        return result.toString();
    }

    @RequestMapping("/index/{count}")
    @ResponseBody
    public String index(@PathVariable Integer count) {

        Result<List<IndexSuggest>> result = new Result<>();
        List<IndexSuggest> list = new ArrayList<>();

        List<VideoSuggest> dyList = videoSuggestService.listByChnlAndPage(1, count, 1, null);
        IndexSuggest indexSuggest1 = new IndexSuggest();
        indexSuggest1.setChannelid(1);
        indexSuggest1.setChannel("精选电影");
        indexSuggest1.setVideos(dyList);

        List<VideoSuggest> dsjList = videoSuggestService.listByChnlAndPage(2, count, 1, null);
        IndexSuggest indexSuggest2 = new IndexSuggest();
        indexSuggest2.setChannelid(2);
        indexSuggest2.setChannel("精选电视剧");
        indexSuggest2.setVideos(dsjList);

        list.add(indexSuggest1);
        list.add(indexSuggest2);

        result.setData(list);
        return result.toString();
    }

    @RequestMapping("/search/{keyword}")
    @ResponseBody
    public String search(@PathVariable String keyword) {

        Result<List<SearchResult>> result = new Result<>();

        if (StringUtils.isEmpty(keyword))
            return result.toString();


        List<SearchResult> searchResults = new ArrayList<>();
        try {
            String response = XHttpClient.doRequest(new HttpGet("http://suggest.video.iqiyi.com/?key=" + URLEncoder.encode(keyword, "utf-8") + "&if=mobile&platform=31&uid=332db807459cd886945c7e87df7965af&ppuid="));
            JSONArray datas = JSONObject.parseObject(response).getJSONArray("data");
            for (int i = 0; datas != null && i < datas.size(); i++) {
                SearchResult searchResult = new SearchResult();
                JSONObject data = datas.getJSONObject(i);
                if (StringUtils.isEmpty(data.getString("link")))
                    continue;
                searchResult.setTitle(data.getString("name"));
                searchResult.setChnl(data.getString("cname"));
                searchResult.setLink(data.getString("link"));
                searchResult.setRegion(data.getString("region"));
                searchResult.setYear(data.getString("year"));

                JSONArray array1 = data.getJSONArray("director");
                StringBuffer directors = new StringBuffer();
                for (int j = 0; array1 != null && j < array1.size(); j++) {
                    directors.append(array1.get(i).toString()).append(" ");
                }
                searchResult.setDirectors(directors.toString());

                JSONArray array2 = data.getJSONArray("main_actor");
                StringBuffer actors = new StringBuffer();
                for (int j = 0; array2 != null && j < array2.size(); j++) {
                    actors.append(array2.get(i).toString()).append(" ");
                }
                searchResult.setActors(actors.toString());

                String pictureLink = data.getString("picture_url").replace(".jpg", "_195_260.jpg");

                CloseableHttpClient httpClient = HttpClients.custom()
                        .build();
                CloseableHttpResponse httpResponse = httpClient.execute(new HttpGet(pictureLink));
                int statusCode = httpResponse.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    searchResult.setPictrue(pictureLink);
                } else {
                    pictureLink = pictureLink.replace("_195_260.jpg", ".jpg");
                    httpResponse = httpClient.execute(new HttpGet(pictureLink));
                    statusCode = httpResponse.getStatusLine().getStatusCode();
                    if (statusCode == 200) {
                        searchResult.setPictrue(pictureLink);
                    } else {
                        logger.error("logo=[{}]找不到");
                        searchResult.setPictrue("http://119.23.39.149/upload/supervip/default.jpg");
                    }
                }
                searchResults.add(searchResult);
            }
        } catch (Exception e) {
            logger.error("error:", e);
        }
        result.setData(searchResults);
        return result.toString();
    }

    class IndexSuggest {
        private String channel;
        private Integer channelid;
        private List<VideoSuggest> videos;

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public Integer getChannelid() {
            return channelid;
        }

        public void setChannelid(Integer channelid) {
            this.channelid = channelid;
        }

        public List<VideoSuggest> getVideos() {
            return videos;
        }

        public void setVideos(List<VideoSuggest> videos) {
            this.videos = videos;
        }
    }

    class SearchResult {
        private String title;
        private String pictrue;
        private String chnl;
        private String link;
        private String directors;
        private String actors;
        private String region;
        private String year;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPictrue() {
            return pictrue;
        }

        public void setPictrue(String pictrue) {
            this.pictrue = pictrue;
        }

        public String getChnl() {
            return chnl;
        }

        public void setChnl(String chnl) {
            this.chnl = chnl;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getDirectors() {
            return directors;
        }

        public void setDirectors(String directors) {
            this.directors = directors;
        }

        public String getActors() {
            return actors;
        }

        public void setActors(String actors) {
            this.actors = actors;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }
    }


}
