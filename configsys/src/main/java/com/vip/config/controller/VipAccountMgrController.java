package com.vip.config.controller;

import com.alibaba.fastjson.JSON;
import com.vip.config.base.BaseController;
import com.vip.config.base.Result;
import com.vip.config.base.ResultDg;
import com.vip.dbservice.model.VipAccount;
import com.vip.dbservice.service.ConfigService;
import com.vip.dbservice.service.VipAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/sys/account")
public class VipAccountMgrController extends BaseController {

    @Autowired
    private VipAccountService vipAccountService;
    @Autowired
    private ConfigService configService;

    @RequestMapping("/fix")
    @ResponseBody
    public String saveOrUpdate(VipAccount vipAccount, String sEffectiveTime) throws ParseException {

        Result<Boolean> result = new Result<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String[] strs = vipAccount.getTypeName().split("_");
        vipAccount.setType(Integer.parseInt(strs[0]));
        vipAccount.setTypeName(strs[1]);
        vipAccount.setEffectiveTime(sdf.parse(sEffectiveTime));
        if (vipAccount.getId() == 0) {
            vipAccount.setCount(configService.getInt("vip.sell.count"));
            vipAccountService.save(vipAccount);
        } else if (vipAccount.getId() > 0) {
            vipAccountService.update(vipAccount);
        }
        result.set(1, true);
        return result.toString();
    }

    @RequestMapping("/get")
    @ResponseBody
    public String get(VipAccount vipAccount) {

        Result<VipAccount> result = new Result<>();

        vipAccount = vipAccountService.get(vipAccount);

        result.set("添加成功", vipAccount);
        return result.toString();
    }

    @RequestMapping("/list")
    @ResponseBody
    public String list() {

        ResultDg<List<VipAccount>> resultDg = new ResultDg<>();

        List<VipAccount> list = vipAccountService.listAll();
        resultDg.setTotal(list == null ? 0 : list.size());
        resultDg.setRows(list);

        return JSON.toJSONString(resultDg);
    }

    @RequestMapping("/del")
    @ResponseBody
    public String del(VipAccount vipAccount) {

        Result<Boolean> result = new Result<>();

        vipAccountService.delete(vipAccount);

        result.set("成功", true);
        return result.toString();
    }

    /**
     * 重置会员及商品数量
     *
     * @return
     */
    @RequestMapping("/count/reset")
    @ResponseBody
    public String reset() {

        Result<Boolean> result = new Result<>();

        vipAccountService.reset();

        result.set("成功", true);
        return result.toString();
    }
}
