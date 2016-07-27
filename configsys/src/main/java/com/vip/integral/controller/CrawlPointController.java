package com.vip.integral.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.vip.integral.base.BaseController;
import com.vip.integral.base.Result;
import com.vip.integral.bean.EasyTreeNode;
import com.vip.integral.model.CrawlPoint;
import com.vip.integral.model.TreeNode;
import com.vip.integral.service.TreeNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/crawl/point")
public class CrawlPointController extends BaseController{

    @Autowired
    private TreeNodeService treeNodeService;

    @RequestMapping("/get")
    @ResponseBody
    public String get(Integer nodeid) {

        Result<CrawlPoint> result = new Result<>();



        return result.toString();
    }



}
