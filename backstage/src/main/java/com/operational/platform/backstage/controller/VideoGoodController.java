package com.operational.platform.backstage.controller;

import com.alibaba.fastjson.JSON;
import com.operational.platform.backstage.base.BaseController;
import com.operational.platform.backstage.base.Result;
import com.operational.platform.backstage.base.ResultDg;
import com.operational.platform.common.util.XHttpClient;
import com.operational.platform.dbservice.model.VideoGood;
import com.operational.platform.dbservice.model.VideoSuggest;
import com.operational.platform.dbservice.service.AttackPageService;
import com.operational.platform.dbservice.service.VideoSuggestService;
import org.apache.http.client.methods.HttpGet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/video/good")
public class VideoGoodController extends BaseController {

    @Autowired
    private VideoSuggestService videoSuggestService;

    @RequestMapping("/list")
    @ResponseBody
    public String list(Integer channel, Integer page, Integer rows, String keyword) {

        ResultDg<List<VideoSuggest>> resultDg = new ResultDg<>();

        List<VideoSuggest> list = videoSuggestService.listByChnlAndPage(channel, rows, page, keyword);

        for (VideoSuggest videoSuggest : list) {

        }

        resultDg.setTotal(videoSuggestService.countByChnlAndPage(channel, keyword).intValue());
        resultDg.setRows(list);

        return JSON.toJSONString(resultDg);
    }

    @RequestMapping("/crawl/{suggestid}")
    @ResponseBody
    public String crawl(@PathVariable Integer suggestid, String link) {

        Result result = new Result();
        result.setCode(-1);

        List list = null;
        if (list.size() > 0) {
            result.setMessage("已提取");
            return result.toString();
        }

        VideoGood videoGood = new VideoGood();
        try {
            String response = XHttpClient.doRequest(new HttpGet(link));
            Document doc = Jsoup.parse(response);
            videoGood.setSuggestid(suggestid);
            videoGood.setTitle(doc.select("body>div.page>div.card>h1.title").get(0).text());
            videoGood.setScore(doc.select("body>div.page>div.card>section.subject-info>div.left>p.rating").get(0).html());
            videoGood.setMeta(doc.select("body>div.page>div.card>section.subject-info>div.left>p.meta").get(0).text());
            videoGood.setImage(doc.select("body>div.page>div.card>section.subject-info>div.right>a>img").get(0).attr("src"));
            videoGood.setDescription(doc.select("body>div.page>div.card>section.subject-intro>div.bd>p").get(0).text());
            //save

            result.setCode(0);
        } catch (Exception e) {
            logger.error("提取豆瓣电影介绍失败[url={}]", link, e);
            result.setMessage("提取豆瓣电影信息失败");
        }


        return result.toString();
    }


}
