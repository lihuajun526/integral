package com.operational.platform.vip.controller;

import com.operational.platform.dbservice.model.Stage;
import com.operational.platform.dbservice.service.StageService;
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
@RequestMapping("/stage")
public class StageController extends BaseController {

    @Autowired
    private StageService stageService;

    @RequestMapping("/list")
    @ResponseBody
    public String list() {

        Result<List<Stage>> result = new Result<>();

        List<Stage> list = stageService.list();

        result.setData(list);

        return result.toString();
    }
}
