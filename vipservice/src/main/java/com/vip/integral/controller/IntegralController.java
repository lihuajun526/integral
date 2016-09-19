package com.vip.integral.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.vip.integral.base.BaseController;
import com.vip.integral.base.Result;
import com.vip.integral.exception.RequestException;
import com.vip.integral.model.Goods;
import com.vip.integral.model.User;
import com.vip.integral.service.UserService;
import com.vip.integral.util.AppConfig;
import com.vip.integral.util.StrUtil;
import com.vip.integral.constant.Constant;
import com.vip.integral.util.XHttpClient;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.DigestException;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by lihuajun on 16-7-6.
 */
@Controller
@RequestMapping("/integral")
public class IntegralController extends BaseController {

    @Autowired
    private AppConfig appConfig;
    @Autowired
    private UserService userService;

    /**
     * 分享到朋友圈获得积分奖励
     *
     * @param userid
     * @param count
     * @return
     */
    @ResponseBody
    @RequestMapping("/share/encourage")
    public String encourageFromShare(Integer userid, Integer count) {


        Result<Boolean> result = new Result<>();

        //result.set(0, userService.getByOpenid(user.getOpenid()));

        return result.toString();
    }


    @RequestMapping("/rec")
    public ModelAndView integralRec() {

        ModelAndView modelAndView = new ModelAndView("integral_rec");
        Goods goods = new Goods();
        goods.setStatus(1);
        //modelAndView.addObject("goodsList", goodsService.listByCondition(goods));
        return modelAndView;
    }

    /**
     * 赚积分
     *
     * @return
     */
    @RequestMapping("/earn")
    public ModelAndView integralEarn(String openid) throws DigestException, UnsupportedEncodingException {

        ModelAndView modelAndView = new ModelAndView("integral_earn");
        User user = userService.getByOpenid(openid);

        //创建二维码ticket
        HttpPost httpPost = new HttpPost("https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + Constant.ACCESS_TOKEN);
        List<NameValuePair> paramList = new ArrayList<>();
        String body = "{\"expire_seconds\": 604800, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": " + user.getId() + "}}}";
        paramList.add(new BasicNameValuePair("body", body));
        httpPost.setEntity(new UrlEncodedFormEntity(paramList, "utf-8"));
        JSONObject jsonObject = null;
        try {
            jsonObject = XHttpClient.doRequest(httpPost);
        } catch (RequestException e) {
            logger.error("创建二维码ticket错误:", e);
        }

        Map param = this.getParam(appConfig.appDomain + "/integral/earn");

        modelAndView.addObject("appId", appConfig.wechatAppid);
        modelAndView.addObject("timestamp", param.get("time"));
        modelAndView.addObject("nonceStr", param.get("noncestr"));
        modelAndView.addObject("signature", param.get("signature"));
        modelAndView.addObject("ticket", jsonObject == null ? "" : URLEncoder.encode(jsonObject.getString("ticket"), "utf-8"));

        return modelAndView;
    }


    private Map<String, String> getParam(String url) throws DigestException {

        String noncestr = StrUtil.getNoncestr();
        Timestamp d = new Timestamp(System.currentTimeMillis());
        String time = Long.toString(d.getTime() / 1000);

        String str = "jsapi_ticket=" + Constant.JSAPI_TICKET + "&noncestr=" + noncestr + "&timestamp=" + time + "&url=" + url;
        logger.info(str);
        String signature = StrUtil.sha1(str);
        logger.info(signature);
        Map<String, String> data = new HashMap<>();
        data.put("noncestr", noncestr);
        data.put("signature", signature);
        data.put("time", time);

        return data;
    }

}
