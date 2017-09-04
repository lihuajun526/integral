package com.operational.platform.backstage.controller;

import com.alibaba.fastjson.JSON;
import com.operational.platform.backstage.base.BaseController;
import com.operational.platform.backstage.base.Result;
import com.operational.platform.backstage.base.ResultDg;
import com.operational.platform.dbservice.model.EmailAccount;
import com.operational.platform.dbservice.service.EmailAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/email/account")
public class EmailAccountController extends BaseController {

    @Autowired
    private EmailAccountService emailAccountService;

    @RequestMapping("/fix")
    @ResponseBody
    public String saveOrUpdate(EmailAccount emailAccount) {

        Result result = new Result<>();

        emailAccountService.save(emailAccount);

        return result.toString();
    }

    @RequestMapping("/list")
    @ResponseBody
    public String list(Integer page, Integer rows) {

        ResultDg<List<EmailAccount>> resultDg = new ResultDg<>();

        List<EmailAccount> list = emailAccountService.listByPage(page, rows);
        resultDg.setTotal(emailAccountService.countByPage());
        resultDg.setRows(list);

        return JSON.toJSONString(resultDg);

    }

    @RequestMapping("/get")
    @ResponseBody
    public String get(Integer id) {

        Result<EmailAccount> result = new Result<>();

        EmailAccount emailAccount = emailAccountService.get(id);
        result.setData(emailAccount);

        return result.toString();
    }

    @RequestMapping("/status/update")
    @ResponseBody
    public String get(Integer id, Integer status) {

        Result result = new Result<>();

        EmailAccount emailAccount = new EmailAccount();
        emailAccount.setId(id);
        emailAccount.setStatus(status);

        emailAccountService.save(emailAccount);
        return result.toString();
    }
}
