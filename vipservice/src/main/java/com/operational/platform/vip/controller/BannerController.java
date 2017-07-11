package com.operational.platform.vip.controller;

import com.operational.platform.dbservice.model.Banner;
import com.operational.platform.dbservice.service.BannerService;
import com.operational.platform.vip.base.BaseController;
import com.operational.platform.vip.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by lihuajun on 16-7-6.
 */
@Controller
@RequestMapping("/banner")
public class BannerController extends BaseController {

    @Autowired
    private BannerService bannerService;

    @RequestMapping("/list/{forum}")
    @ResponseBody
    public String listByForum(@PathVariable Integer forum) {

        Result<List<Banner>> result = new Result<>();

        List<Banner> list = bannerService.listByForum(forum);

        result.setData(list);

        return result.toString();
    }
}
