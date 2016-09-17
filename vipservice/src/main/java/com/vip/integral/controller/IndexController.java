package com.vip.integral.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.vip.integral.base.BaseController;
import com.vip.integral.constant.Constant;
import com.vip.integral.exception.RequestException;
import com.vip.integral.util.Config;
import com.vip.integral.util.XHttpClient;
import org.apache.http.client.methods.HttpGet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by lihuajun on 16-7-6.
 */
@Controller
@RequestMapping("/index")
public class IndexController extends BaseController {

    /**
     * 查找所有上架商品
     *
     * @return
     */
    @RequestMapping("/suggest")
    public ModelAndView suggest(String type, String code) {

        ModelAndView modelAndView = new ModelAndView("suggest");

        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + Config.get("wechat.appid") + "&secret=" + Config.get("wechat.secret") + "&code=" + code + "&grant_type=authorization_code";
        HttpGet httpGet = new HttpGet(url);
        try {
            String response = XHttpClient.doRequest(httpGet);
            JSONObject jsonObject = JSON.parseObject(response);
            modelAndView.addObject("openid", jsonObject.getString("openid"));
        } catch (RequestException e) {
            e.printStackTrace();
        }

        modelAndView.addObject("type", type);
        return modelAndView;
    }


}
