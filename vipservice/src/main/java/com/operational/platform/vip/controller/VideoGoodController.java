package com.operational.platform.vip.controller;

import com.operational.platform.dbservice.model.VideoGood;
import com.operational.platform.dbservice.service.VideoGoodService;
import com.operational.platform.vip.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by lihuajun on 16-7-6.
 */
@Controller
@RequestMapping("/video/good")
public class VideoGoodController extends BaseController {

    @Autowired
    private VideoGoodService videoGoodService;

    @RequestMapping("/get/{id}")
    @ResponseBody
    public ModelAndView get(@PathVariable Integer id) {

        ModelAndView mv = new ModelAndView("video_intru");

        VideoGood videoGood = videoGoodService.get(id);
        mv.addObject("videoGood",videoGood);

        return mv;

    }

    @RequestMapping("/get/send/{id}")
    @ResponseBody
    public ModelAndView getSend(@PathVariable Integer id) {

        ModelAndView mv = new ModelAndView("video_intru_send");

        VideoGood videoGood = videoGoodService.get(id);
        mv.addObject("videoGood",videoGood);

        return mv;

    }

}
