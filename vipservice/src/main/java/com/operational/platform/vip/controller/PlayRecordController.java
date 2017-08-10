package com.operational.platform.vip.controller;

import com.operational.platform.common.constant.VipPlatform;
import com.operational.platform.common.exception.RequestException;
import com.operational.platform.common.util.XHttpClient;
import com.operational.platform.dbservice.model.PlayRecord;
import com.operational.platform.dbservice.model.User;
import com.operational.platform.dbservice.service.PlayRecordService;
import com.operational.platform.vip.base.BaseController;
import com.operational.platform.vip.base.Result;
import com.operational.platform.vip.constant.Constant;
import org.apache.http.client.methods.HttpGet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lihuajun on 16-7-6.
 */
@Controller
@RequestMapping("/play/record")
public class PlayRecordController extends BaseController {

    @Autowired
    private PlayRecordService playRecordService;

    /**
     * 保存播放记录
     *
     * @return
     */
    @RequestMapping("/save/v_login")
    @ResponseBody
    public String save(String url, String vipAccessToken) {

        Result result = new Result<>();

        User loginUser = Constant.SessionMap.get(vipAccessToken);

        PlayRecord playRecord = new PlayRecord();
        try {
            playRecord.setUrl(URLDecoder.decode(url, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            logger.error("解码失败[{}]", url);
            playRecord.setUrl(url);
        }
        playRecord.setUserid(loginUser.getId());

        playRecordService.save(playRecord);

        //启动一个线程爬取视频的标题和图片
        new Thread(new CrawlVideo(playRecordService, playRecord)).start();

        return result.toString();
    }

    /**
     * 获取最近播放记录
     *
     * @return
     */
    @RequestMapping("/list/latest/v_login")
    @ResponseBody
    public String listLatest(String vipAccessToken) {

        Result<List<PlayRecord>> result = new Result<>();

        User loginUser = Constant.SessionMap.get(vipAccessToken);

        List<PlayRecord> list = playRecordService.listLatestByUser(loginUser);
        result.setData(list);

        return result.toString();
    }
}

class CrawlVideo implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrawlVideo.class);

    private PlayRecord playRecord;
    private PlayRecordService playRecordService;

    public CrawlVideo(PlayRecordService playRecordService, PlayRecord playRecord) {
        this.playRecord = playRecord;
        this.playRecordService = playRecordService;
    }

    @Override
    public void run() {
        String url = playRecord.getUrl();
        Map<String, String> map = null;

        if (url.indexOf(VipPlatform.Iqy.domain) != -1) {
            map = aqy();
        } else if (url.indexOf(VipPlatform.Youku.domain) != -1) {
            map = mgtv();
        } else if (url.indexOf(VipPlatform.Txsp.domain) != -1) {

        } else if (url.indexOf(VipPlatform.PPLive.domain) != -1) {

        } else if (url.indexOf(VipPlatform.Mgtv.domain) != -1) {

        }

        String title = map.get("title");
        String image = map.get("image");
        if (StringUtils.isEmpty(title) || StringUtils.isEmpty(image)) {
            LOGGER.error("无法爬取标题或图片url=[{}]", playRecord.getUrl());
        }
        if (StringUtils.isEmpty(title) || StringUtils.isEmpty(image))
            return;
        playRecord.setTitle(title);
        playRecord.setImage(image);
        playRecordService.save(playRecord);
    }

    private Map<String, String> aqy() {
        Map<String, String> map = new HashMap<>();
        try {
            String response = XHttpClient.doRequest(new HttpGet(playRecord.getUrl()));
            Document doc = Jsoup.parse(response);
            Elements elements = doc.select("meta[property=og:title]");
            if (elements != null && elements.size() > 0)
                map.put("title", elements.get(0).attr("content"));

            elements = doc.select("meta[property=og:image]");
            if (elements != null && elements.size() > 0)
                map.put("image", elements.get(0).attr("content"));

        } catch (RequestException e) {
            LOGGER.error("提取标题或图片错误[{}]:", playRecord.getUrl(), e);
        }
        return map;
    }

    private Map<String, String> mgtv() {
        Map<String, String> map = new HashMap<>();
        try {
            String response = XHttpClient.doRequest(new HttpGet(playRecord.getUrl()));
            Document doc = Jsoup.parse(response);
            Elements elements = doc.select("title");
            if (elements != null && elements.size() > 0) {
                String title = elements.get(0).text();
                title = title.split(" - ")[0];
                map.put("title", title);
            }
        } catch (RequestException e) {
            LOGGER.error("提取标题或图片错误[{}]:", playRecord.getUrl(), e);
        }
        return map;
    }
}
