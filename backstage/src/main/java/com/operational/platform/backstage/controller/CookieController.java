package com.operational.platform.backstage.controller;

import com.operational.platform.backstage.base.BaseController;
import com.operational.platform.backstage.base.Result;
import com.operational.platform.dbservice.model.AttackParam;
import com.operational.platform.dbservice.service.AttackParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/attack/param")
public class CookieController extends BaseController {

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
