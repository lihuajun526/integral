package com.operational.platform.vip.controller;

import com.operational.platform.dbservice.model.VideoSuggest;
import com.operational.platform.dbservice.service.VideoSuggestService;
import com.operational.platform.vip.base.BaseController;
import com.operational.platform.vip.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

        List<VideoSuggest> list = videoSuggestService.listByChnlAndPage(channelid, pagesize, pageindex);
        long total = videoSuggestService.countByChnlAndPage(channelid);
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

        List<VideoSuggest> dyList = videoSuggestService.listByChnlAndPage(1, count, 1);
        IndexSuggest indexSuggest1 = new IndexSuggest();
        indexSuggest1.setChannelid(1);
        indexSuggest1.setChannel("精选电影");
        indexSuggest1.setVideos(dyList);

        List<VideoSuggest> dsjList = videoSuggestService.listByChnlAndPage(2, count, 1);
        IndexSuggest indexSuggest2 = new IndexSuggest();
        indexSuggest2.setChannelid(2);
        indexSuggest2.setChannel("精选电视剧");
        indexSuggest2.setVideos(dsjList);

        list.add(indexSuggest1);
        list.add(indexSuggest2);

        result.setData(list);
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
}
