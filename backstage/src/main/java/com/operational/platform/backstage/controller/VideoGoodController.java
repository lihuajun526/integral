package com.operational.platform.backstage.controller;

import com.alibaba.fastjson.JSON;
import com.operational.platform.backstage.base.BaseController;
import com.operational.platform.backstage.base.Result;
import com.operational.platform.backstage.base.ResultDg;
import com.operational.platform.common.util.Config;
import com.operational.platform.common.util.Downloader;
import com.operational.platform.common.util.XHttpClient;
import com.operational.platform.dbservice.model.VideoGood;
import com.operational.platform.dbservice.model.VideoSuggest;
import com.operational.platform.dbservice.service.VideoGoodService;
import com.operational.platform.dbservice.service.VideoSuggestService;
import org.apache.http.client.methods.HttpGet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.List;

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/video/good")
public class VideoGoodController extends BaseController {

    @Autowired
    private VideoSuggestService videoSuggestService;
    @Autowired
    private VideoGoodService videoGoodService;

    @RequestMapping("/list")
    @ResponseBody
    public String list(Integer channel, Integer page, Integer rows, String keyword) {

        ResultDg<List<VideoSuggest>> resultDg = new ResultDg<>();

        List<VideoSuggest> list = videoSuggestService.listByChnlAndPage(channel, rows, page, keyword);

        for (VideoSuggest videoSuggest : list) {
            if (videoSuggest.getManual() == 0)
                continue;
            VideoGood videoGood = videoGoodService.getBySuggest(videoSuggest.getId());
            if (videoGood != null)
                videoSuggest.setDbLink(videoGood.getUrl());
        }

        resultDg.setTotal(videoSuggestService.countByChnlAndPage(channel, keyword).intValue());
        resultDg.setRows(list);

        return JSON.toJSONString(resultDg);
    }

    @RequestMapping("/crawl/{suggestid}")
    @ResponseBody
    public String crawl(@PathVariable Integer suggestid, String link) {

        Result<VideoGood> result = new Result();
        result.setCode(-1);

        VideoSuggest videoSuggest = videoSuggestService.get(suggestid);

        VideoGood videoGood = videoGoodService.getBySuggest(suggestid);
        if (videoGood == null)
            videoGood = new VideoGood();

        try {
            String response = XHttpClient.doRequest(new HttpGet(link.trim()));
            Document doc = Jsoup.parse(response);
            videoGood.setSuggestid(suggestid);
            videoGood.setTitle(videoSuggest.getTitle());
            videoGood.setScore(doc.select("body>div.page>div.card>section.subject-info>div.left>p.rating").get(0).html());
            videoGood.setMeta(doc.select("body>div.page>div.card>section.subject-info>div.left>p.meta").get(0).text());

            String photoUrl = videoSuggest.getPhoto();
            String suffix = photoUrl.substring(photoUrl.lastIndexOf("."));
            String fileName = System.currentTimeMillis() + suffix;
            String filePath = Config.get("video.good.path") + File.separator + fileName;
            Downloader.file(videoSuggest.getPhoto(), filePath);
            videoGood.setImage("http://www.yka365.com/upload/supervip/goodVideo/" + fileName);

            videoGood.setDescription(videoSuggest.getDescription());
            videoGood.setUrl(link);
            videoGoodService.save(videoGood);

            result.setCode(0);
            result.setData(videoGood);
        } catch (Exception e) {
            logger.error("提取豆瓣电影介绍失败[url={}]", link, e);
            result.setMessage("提取豆瓣电影信息失败");
        }

        return result.toString();
    }

    @RequestMapping("/set/{id}")
    @ResponseBody
    public String setGood(@PathVariable Integer id) {

        Result result = new Result();

        videoSuggestService.setMaxManual(id);

        return result.toString();
    }

    @RequestMapping("/no/{id}")
    @ResponseBody
    public String noGood(@PathVariable Integer id) {

        Result result = new Result();

        videoSuggestService.setMinManual(id);

        return result.toString();
    }

    @RequestMapping("/getBySuggest/{id}")
    @ResponseBody
    public String getBySuggest(@PathVariable Integer id) {

        Result<VideoGood> result = new Result();

        VideoGood videoGood = videoGoodService.getBySuggest(id);
        if (videoGood != null)
            result.setData(videoGood);

        return result.toString();
    }
}
