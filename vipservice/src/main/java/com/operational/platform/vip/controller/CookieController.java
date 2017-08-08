package com.operational.platform.vip.controller;

import com.alibaba.fastjson.JSONObject;
import com.operational.platform.common.constant.AttackType;
import com.operational.platform.common.constant.ExceptionCode;
import com.operational.platform.common.constant.VipPlatform;
import com.operational.platform.dbservice.model.AttackParamWithBLOBs;
import com.operational.platform.dbservice.model.User;
import com.operational.platform.dbservice.model.UserCookieMap;
import com.operational.platform.dbservice.service.AttackParamService;
import com.operational.platform.dbservice.service.UserCookieMapService;
import com.operational.platform.vip.base.BaseController;
import com.operational.platform.vip.base.Result;
import com.operational.platform.vip.constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/cookie")
public class CookieController extends BaseController {

    @Autowired
    private AttackParamService attackParamService;
    @Autowired
    private UserCookieMapService userCookieMapService;

    @RequestMapping("/get/v_login")
    @ResponseBody
    public String get(String url, String vipAccessToken) {

        Result<Map<String, Object>> result = new Result<>();
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

        User loginUser = Constant.SessionMap.get(vipAccessToken);
        String belong = "";
        if (url.indexOf(VipPlatform.Iqy.domain) != -1) {
            belong = VipPlatform.Iqy.name;
        } else if (url.indexOf(VipPlatform.Youku.domain) != -1) {
            belong = VipPlatform.Youku.name;
        } else if (url.indexOf(VipPlatform.Txsp.domain) != -1) {
            belong = VipPlatform.Txsp.name;
        } else if (url.indexOf(VipPlatform.PPLive.domain) != -1) {
            belong = VipPlatform.PPLive.name;
        } else if (url.indexOf(VipPlatform.Mgtv.domain) != -1) {
            belong = VipPlatform.Mgtv.name;
        }
        if (StringUtils.isEmpty(belong)) {
            logger.error("无法判断该url所属平台[{}]", url);
            result.set(ExceptionCode.GET_STAGE_TYPE_ERROR.code, "无法获得所属平台");
            return result.toString();
        }

        Map<String, Object> data = new HashMap<>();
        UserCookieMap userCookieMap = userCookieMapService.getByUserAndBelong(loginUser.getId(), belong);
        if (userCookieMap != null) {
            AttackParamWithBLOBs attackParam = attackParamService.get(userCookieMap.getCookieid());
            data.put("cookieid", attackParam.getId());
            data.put("cookie", JSONObject.parseObject(attackParam.getCookies()).getString(AttackType.Player.value));
            result.setData(data);
            return result.toString();
        }

        List<AttackParamWithBLOBs> list = null;
        list = attackParamService.listByBelongAndAttackType(belong, AttackType.Player.value);
        if (list.size() == 0) {
            result.set(ExceptionCode.Result_IS_NULL_ERROR.code, "结果为空");
            return result.toString();
        }
        AttackParamWithBLOBs attackParam = list.get(0);
        data.put("cookieid", attackParam.getId());
        data.put("cookie", JSONObject.parseObject(attackParam.getCookies()).getString(AttackType.Player.value));
        result.setData(data);

        //更新使用次数
        attackParam.setNum(attackParam.getNum() + 1);
        attackParamService.update(attackParam);
        //新增一条UserCookieMap
        userCookieMap = new UserCookieMap();
        userCookieMap.setUserid(loginUser.getId());
        userCookieMap.setCookieid(attackParam.getId());
        userCookieMap.setBelong(belong);
        userCookieMapService.save(userCookieMap);

        return result.toString();
    }


    @RequestMapping("/time/update/v_login")
    @ResponseBody
    public String updateTime(String vipAccessToken, Integer cookieid) {

        Result result = new Result();

        User loginUser = Constant.SessionMap.get(vipAccessToken);

        UserCookieMap userCookieMap = new UserCookieMap();
        userCookieMap.setUserid(loginUser.getId());
        userCookieMap.setCookieid(cookieid);
        userCookieMap.setUpdateTime(new Date());

        userCookieMapService.updateByUserAndCookie(userCookieMap);

        result.setMessage("请求成功");
        return result.toString();
    }


}
