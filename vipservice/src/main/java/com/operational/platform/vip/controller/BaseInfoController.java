package com.operational.platform.vip.controller;

import com.alibaba.fastjson.JSONObject;
import com.operational.platform.dbservice.model.AppVersion;
import com.operational.platform.dbservice.model.Regular;
import com.operational.platform.dbservice.model.User;
import com.operational.platform.dbservice.service.BannerService;
import com.operational.platform.dbservice.service.BaseInfoService;
import com.operational.platform.dbservice.service.ConfigService;
import com.operational.platform.dbservice.service.UserService;
import com.operational.platform.vip.base.BaseController;
import com.operational.platform.vip.base.Result;
import com.operational.platform.vip.constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lihuajun on 16-7-6.
 */
@Controller
public class BaseInfoController extends BaseController {

    @Autowired
    private BaseInfoService baseInfoService;
    @Autowired
    private ConfigService configService;
    @Autowired
    private UserService userService;
    @Autowired
    private BannerService bannerService;

    @ResponseBody
    @RequestMapping("/base/info")
    public String getBaseInfo(String appType, String vipAccessToken) {

        Result<Map<String, Object>> result = new Result<>();
        Map<String, Object> data = new HashMap<>();

        AppVersion appVersion = baseInfoService.getLatestVersion(appType);
        List<Regular> regulars = baseInfoService.listAll();

        data.put("version", appVersion);
        data.put("regulars", regulars);
        data.put("banners", bannerService.listByForum(1));
        if (!StringUtils.isEmpty(vipAccessToken)) {
            User loginUser = Constant.SessionMap.get(vipAccessToken);
            if (loginUser == null)
                loginUser = userService.getByAccessToken(vipAccessToken);
            if (loginUser != null) {
                loginUser = userService.get(loginUser.getId());
                if (loginUser != null) {
                    data.put("vipExpires", loginUser.getVipExpires());
                    data.put("integral", loginUser.getIntegral());
                    Constant.SessionMap.put(vipAccessToken, loginUser);
                }
            }
        }
        List<String> descList = new ArrayList<>();
        String buyDesc = configService.getString("buy.desc");
        for (String desc : buyDesc.split("#")) {
            descList.add(desc);
        }
        data.put("buyDesc", descList);

        String linkWay = configService.getString("link.way");
        data.put("linkWay", JSONObject.parseObject(linkWay));

        result.setData(data);
        return result.toString();
    }

    /**
     * 是否显示小红点
     *
     * @param vipAccessToken
     * @return
     */
    @ResponseBody
    @RequestMapping("/other/info/reddot")
    public String getBaseInfo(String vipAccessToken) {

        Result<Boolean> result = new Result<>();
        result.setData(false);

        if (StringUtils.isEmpty(vipAccessToken)) {
            return result.toString();
        }

        User loginUser = Constant.SessionMap.get(vipAccessToken);

        result.setData(true);
        return result.toString();
    }


}
