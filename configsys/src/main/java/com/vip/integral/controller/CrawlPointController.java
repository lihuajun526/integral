package com.vip.integral.controller;

import com.vip.integral.base.BaseController;
import com.vip.integral.base.Result;
import com.vip.integral.model.CrawlPoint;
import com.vip.integral.service.CrawlPointService;
import com.vip.integral.service.TreeNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/crawl/point")
public class CrawlPointController extends BaseController {

    @Autowired
    private CrawlPointService crawlPointService;

    @RequestMapping("/get")
    @ResponseBody
    public String get(Integer nodeid) {

        Result<CrawlPoint> result = new Result<>();

        CrawlPoint crawlPoint = crawlPointService.getByNode(nodeid);
        result.setData(crawlPoint);

        return result.toString();
    }

    @RequestMapping("/save")
    @ResponseBody
    public String save(CrawlPoint crawlPoint) {

        Result<Boolean> result = new Result<>();

        crawlPointService.save(crawlPoint);
        result.setData(true);
        result.setMessage("添加成功");

        return result.toString();
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(CrawlPoint crawlPoint) {

        Result<Boolean> result = new Result<>();

        crawlPointService.update(crawlPoint);
        result.setData(true);
        result.setMessage("更新成功");

        return result.toString();
    }

}
