package com.operational.platform.vip.controller;

import com.alibaba.fastjson.JSONObject;

import com.operational.platform.common.constant.AttackType;
import com.operational.platform.common.constant.ExceptionCode;
import com.operational.platform.common.constant.VipPlatform;
import com.operational.platform.dbservice.model.AttackParamWithBLOBs;
import com.operational.platform.dbservice.service.AttackParamService;
import com.operational.platform.vip.base.BaseController;
import com.operational.platform.vip.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/cookie")
public class CookieController extends BaseController {

    @Autowired
    private AttackParamService attackParamService;

    @RequestMapping("/get")
    @ResponseBody
    public String get(String url) {

        Result<String> result = new Result<>();
        if (StringUtils.isEmpty(url)) {
            result.set(ExceptionCode.PARAM_IS_NULL_ERROR.code, "url不能为空");
            return result.toString();
        }
        try {
            logger.debug("url={}", url);
            url = URLDecoder.decode(url, "utf-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("error:", e);
        }
        List<AttackParamWithBLOBs> list = null;
        if (url.indexOf(VipPlatform.Iqy.domain) != -1) {
            list = attackParamService.listByBelongAndAttackType(VipPlatform.Iqy.name, AttackType.Player.value);
        } else if (url.indexOf(VipPlatform.Youku.domain) != -1) {
            list = attackParamService.listByBelongAndAttackType(VipPlatform.Youku.name, AttackType.Player.value);
        } else if (url.indexOf(VipPlatform.Txsp.domain) != -1) {
            list = attackParamService.listByBelongAndAttackType(VipPlatform.Txsp.name, AttackType.Player.value);
        }
        if (list == null || list.size() == 0) {
            result.set(ExceptionCode.Result_IS_NULL_ERROR.code, "结果为空");
            return result.toString();
        }
        AttackParamWithBLOBs attackParam = list.get(0);
        result.setData(JSONObject.parseObject(attackParam.getCookies()).getString(AttackType.Player.value));
        return result.toString();
    }

    public static void main(String[] args) {
        String url = "http://119.23.39.149:8090/vipservice/cookie/get?url=http://www.iqiyi.com/v_19rr7sz5b8.html?fc=87bbded392d221f5";
        try {
            url = URLEncoder.encode(url, "UTF-8");
            System.out.println(url);
            System.out.println(URLDecoder.decode(url, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }


}