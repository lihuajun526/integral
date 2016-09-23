package com.vip.config.controller;

import com.vip.config.base.BaseController;
import com.vip.config.base.Result;
import com.vip.dbservice.model.AttackParam;
import com.vip.dbservice.service.AttackParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/attack/param")
public class AttackParamController extends BaseController {

    @Autowired
    private AttackParamService attackParamService;

    @RequestMapping("/get")
    @ResponseBody
    public String get(Integer nodeid) {

        Result<AttackParam> result = new Result<>();

        AttackParam attackParam = attackParamService.getByNode(nodeid);
        result.setData(attackParam);

        return result.toString();
    }

    @RequestMapping("/save")
    @ResponseBody
    public String save(AttackParam attackParam) {

        Result<Boolean> result = new Result<>();

        attackParamService.save(attackParam);
        result.setData(true);
        result.setMessage("添加成功");

        return result.toString();
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(AttackParam attackParam) {

        Result<Boolean> result = new Result<>();

        attackParamService.update(attackParam);
        result.setData(true);
        result.setMessage("更新成功");

        return result.toString();
    }

}
