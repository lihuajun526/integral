package com.operational.platform.backstage.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.operational.platform.backstage.base.BaseController;
import com.operational.platform.backstage.base.Result;
import com.operational.platform.backstage.base.ResultDg;
import com.operational.platform.dbservice.model.AttackPage;
import com.operational.platform.dbservice.model.VideoSuggest;
import com.operational.platform.dbservice.service.AttackPageService;
import com.operational.platform.dbservice.service.VideoSuggestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/video/suggest")
public class VideoSuggestController extends BaseController {

    @Autowired
    private VideoSuggestService videoSuggestService;
    @Autowired
    private AttackPageService attackPageService;

    @RequestMapping("/list")
    @ResponseBody
    public String list() {

        ResultDg<List<VideoSuggest>> resultDg = new ResultDg<>();

        List<VideoSuggest> list = videoSuggestService.listAll();
        resultDg.setTotal(list.size());
        resultDg.setRows(list);

        return JSON.toJSONString(resultDg);
    }

    @RequestMapping("/status/update")
    @ResponseBody
    public String updateStatus(VideoSuggest videoSuggest) {

        Result<Boolean> result = new Result<>();

        videoSuggestService.update(videoSuggest);

        result.set("成功", true);
        return result.toString();
    }

    @RequestMapping("/syn")
    @ResponseBody
    public String syn() {

        Result result = new Result<>();

        List<String> belongs = new ArrayList<>();
        belongs.add("youku");
        belongs.add("aqy");
        belongs.add("pptv");
        belongs.add("mgtv");
        belongs.add("souhu");
        List<AttackPage> list = attackPageService.listByBelongs(belongs);

        for (AttackPage attackPage : list) {
            VideoSuggest videoSuggest = new VideoSuggest();
            videoSuggest.setTitle(attackPage.getTitle());
            videoSuggest.setUrl(attackPage.getLink());
            JSONObject attr = JSONObject.parseObject(attackPage.getAttr());
            videoSuggest.setPhoto(attr.getString("logo"));
            videoSuggest.setScore(attr.getString("score"));
            videoSuggest.setDescription(attr.getString("desc"));
            videoSuggest.setStatus(0);

            videoSuggestService.save(videoSuggest);
        }
        result.setMessage("成功");
        return result.toString();
    }
}
