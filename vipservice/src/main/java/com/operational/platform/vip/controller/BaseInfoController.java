package com.operational.platform.vip.controller;

import com.operational.platform.dbservice.model.AppVersion;
import com.operational.platform.dbservice.model.Regular;
import com.operational.platform.dbservice.service.BaseInfoService;
import com.operational.platform.vip.base.BaseController;
import com.operational.platform.vip.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @ResponseBody
    @RequestMapping("/base/info/{appType}")
    public String getBaseInfo(@PathVariable String appType) {

        Result<Map<String,Object>> result = new Result<>();
        Map<String,Object> data = new HashMap<>();

        AppVersion appVersion = baseInfoService.getLatestVersion(appType);
        List<Regular> regulars = baseInfoService.listAll();

        data.put("version",appVersion);
        data.put("regulars",regulars);

        result.setData(data);

        return result.toString();
    }


}
