package com.operational.platform.vip.controller;

import com.operational.platform.dbservice.model.VideoSuggest;
import com.operational.platform.dbservice.service.VideoSuggestService;
import com.operational.platform.vip.base.BaseController;
import com.operational.platform.vip.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by lihuajun on 16-7-6.
 */
@Controller
@RequestMapping("/video/suggest")
public class VideoSuggestController extends BaseController {

    @Autowired
    private VideoSuggestService videoSuggestService;

    @RequestMapping("/list")
    @ResponseBody
    public String list() {

        Result<List<VideoSuggest>> result = new Result<>();

        List<VideoSuggest> list = videoSuggestService.listByStatus(1);

        result.setData(list);

        return result.toString();
    }
}
