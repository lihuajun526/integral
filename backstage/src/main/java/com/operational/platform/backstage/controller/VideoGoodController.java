package com.operational.platform.backstage.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.operational.platform.backstage.base.BaseController;
import com.operational.platform.backstage.base.Result;
import com.operational.platform.backstage.base.ResultDg;
import com.operational.platform.common.exception.CommonException;
import com.operational.platform.common.exception.RequestException;
import com.operational.platform.common.util.Config;
import com.operational.platform.common.util.Downloader;
import com.operational.platform.common.util.XHttpClient;
import com.operational.platform.dbservice.model.*;
import com.operational.platform.dbservice.service.VideoGoodService;
import com.operational.platform.dbservice.service.VideoSuggestService;
import org.apache.http.client.methods.HttpGet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.ArrayList;
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
        String dbId = link.split("/movie/subject/")[1].replaceAll("/", "");

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
            photoUrl = doc.select("html>head>meta[property=weixin:image]").get(0).attr("content");
            suffix = ".jpg";
            fileName = System.currentTimeMillis() + suffix;
            filePath = Config.get("video.good.path") + File.separator + fileName;
            Downloader.file(photoUrl, filePath);
            videoGood.setWimage("http://www.yka365.com/upload/supervip/goodVideo/" + fileName);

            videoGood.setShortComments(crawlShortComments(dbId));
            videoGood.setVideoTags(crawlVideoTags(doc));
            videoGood.setVideoRelations(crawlVideoRelations(doc));
            videoGood.setVideoImages(crawlVideoImages(doc));
            videoGood.setVideoPersons(crawlVideoPersons(dbId));

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

    private List<DbVideoPerson> crawlVideoPersons(String dbId) throws CommonException, RequestException {
        List<DbVideoPerson> list = new ArrayList<>();

        String response = XHttpClient.doRequest(new HttpGet("https://m.douban.com/rexxar/api/v2/movie/" + dbId + "/credits"));
        JSONArray jsonArray = JSON.parseObject(response).getJSONArray("credits");
        JSONArray directors = null;
        JSONArray actors = null;
        for (int i = 0; i < jsonArray.size(); i++) {
            if (jsonArray.getJSONObject(i).getString("title").equals("导演")) {
                directors = jsonArray.getJSONObject(i).getJSONArray("celebrities");
            }
            if (jsonArray.getJSONObject(i).getString("title").equals("演员")) {
                actors = jsonArray.getJSONObject(i).getJSONArray("celebrities");
            }
        }

        if (directors != null) {
            for (int i = 0; i < directors.size(); i++) {
                JSONObject jsonObj = directors.getJSONObject(i);
                DbVideoPerson videoPerson = new DbVideoPerson();
                videoPerson.setLink(jsonObj.getString("url"));
                videoPerson.setName(jsonObj.getString("name"));
                videoPerson.setPosition("导演");
                String photo = jsonObj.getString("cover_url").replaceAll("\\?imageView2/2/q/90/w/400/h/400/format/jpg", "");
                if (photo.equals("https://img1.doubanio.com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png")) {
                    photo = "http://www.yka365.com/upload/supervip/goodVideo/default.png";
                    videoPerson.setPhoto(photo);
                } else {
                    String suffix = photo.substring(photo.lastIndexOf("."));
                    String fileName = System.currentTimeMillis() + suffix;
                    String filePath = Config.get("video.good.path") + File.separator + fileName;
                    Downloader.file(photo, filePath);
                    videoPerson.setPhoto("http://www.yka365.com/upload/supervip/goodVideo/" + fileName);
                }

                list.add(videoPerson);
            }
        }

        if (actors != null) {
            for (int i = 0; i < actors.size(); i++) {
                JSONObject jsonObj = actors.getJSONObject(i);
                DbVideoPerson videoPerson = new DbVideoPerson();
                videoPerson.setLink(jsonObj.getString("url"));
                videoPerson.setName(jsonObj.getString("name"));
                videoPerson.setPosition("演员");

                String photo = jsonObj.getString("cover_url").replaceAll("\\?imageView2/2/q/90/w/400/h/400/format/jpg", "");
                if (photo.equals("https://img1.doubanio.com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png")) {
                    photo = "http://www.yka365.com/upload/supervip/goodVideo/default.png";
                    videoPerson.setPhoto(photo);
                } else {
                    String suffix = photo.substring(photo.lastIndexOf("."));
                    String fileName = System.currentTimeMillis() + suffix;
                    String filePath = Config.get("video.good.path") + File.separator + fileName;
                    Downloader.file(photo, filePath);
                    videoPerson.setPhoto("http://www.yka365.com/upload/supervip/goodVideo/" + fileName);
                }

                list.add(videoPerson);
            }
        }

        return list;
    }

    private List<DbShortComment> crawlShortComments(String dbId) throws CommonException, RequestException {
        List<DbShortComment> list = new ArrayList<>();

        String response = XHttpClient.doRequest(new HttpGet("https://m.douban.com/rexxar/api/v2/movie/" + dbId + "/interests?count=20&order_by=hot&start=0&ck=&for_mobile=1"));
        JSONArray jsonArray = JSON.parseObject(response).getJSONArray("interests");
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObj = jsonArray.getJSONObject(i);
            DbShortComment shortComment = new DbShortComment();

            shortComment.setName(jsonObj.getJSONObject("user").getString("name"));
            shortComment.setPhoto(jsonObj.getJSONObject("user").getString("avatar"));
            shortComment.setDateTime(jsonObj.getString("create_time"));
            if (jsonObj.getJSONObject("rating") != null)
                shortComment.setStar(jsonObj.getJSONObject("rating").getInteger("value"));
            else
                shortComment.setStar(1);
            shortComment.setContent(jsonObj.getString("comment"));
            shortComment.setPraise(jsonObj.getInteger("vote_count"));

            String photo = shortComment.getPhoto();
            String suffix = photo.substring(photo.lastIndexOf("."));
            String fileName = System.currentTimeMillis() + suffix;
            String filePath = Config.get("video.good.path") + File.separator + fileName;
            Downloader.file(photo, filePath);
            shortComment.setPhoto("http://www.yka365.com/upload/supervip/goodVideo/" + fileName);

            list.add(shortComment);
        }

        return list;
    }

    private List<DbVideoTag> crawlVideoTags(Document doc) throws CommonException {
        List<DbVideoTag> list = new ArrayList<>();

        Elements elements = doc.select("section.tags>ul>li>a");
        for (int i = 0; i < elements.size(); i++) {
            DbVideoTag videoTag = new DbVideoTag();
            Element e = elements.get(i);
            videoTag.setLink(e.attr("href"));
            videoTag.setTitle(e.text().trim());

            list.add(videoTag);
        }

        return list;
    }

    private List<DbVideoImage> crawlVideoImages(Document doc) throws CommonException {
        List<DbVideoImage> list = new ArrayList<>();

        Elements elements = doc.select("section.subject-pics>div.bd>ul.wx-preview>li.pic>a");
        for (int i = 0; i < elements.size(); i++) {
            DbVideoImage videoImage = new DbVideoImage();
            Element e = elements.get(i);
            videoImage.setLink(e.attr("href"));

            String photo = e.select("img").get(0).attr("data-src");
            String suffix = photo.substring(photo.lastIndexOf("."));
            String fileName = System.currentTimeMillis() + suffix;
            String filePath = Config.get("video.good.path") + File.separator + fileName;
            Downloader.file(photo, filePath);
            videoImage.setImage("http://www.yka365.com/upload/supervip/goodVideo/" + fileName);

            list.add(videoImage);
        }

        return list;
    }

    private List<DbVideoRelation> crawlVideoRelations(Document doc) throws CommonException {
        List<DbVideoRelation> list = new ArrayList<>();

        Elements elements = doc.select("section.subject-rec>div.bd>ul>li>a");
        for (int i = 0; i < elements.size(); i++) {
            DbVideoRelation videoRelation = new DbVideoRelation();
            Element e = elements.get(i);
            videoRelation.setLink(e.attr("href"));
            videoRelation.setTitle(e.select("div.wp>img").get(0).attr("alt"));

            String photo = e.select("div.wp>img").get(0).attr("data-src");
            String suffix = photo.substring(photo.lastIndexOf("."));
            String fileName = System.currentTimeMillis() + suffix;
            String filePath = Config.get("video.good.path") + File.separator + fileName;
            Downloader.file(photo, filePath);
            videoRelation.setImage("http://www.yka365.com/upload/supervip/goodVideo/" + fileName);

            list.add(videoRelation);
        }

        return list;
    }
}
