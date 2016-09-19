package com.vip.integral.controller;

import com.alibaba.fastjson.JSONObject;
import com.vip.integral.base.BaseController;
import com.vip.integral.exception.OrderException;
import com.vip.integral.exception.RequestException;
import com.vip.integral.model.Goods;
import com.vip.integral.model.User;
import com.vip.integral.service.ConfigService;
import com.vip.integral.service.GoodsService;
import com.vip.integral.service.UserService;
import com.vip.integral.util.AppConfig;
import com.vip.integral.util.XHttpClient;
import org.apache.http.client.methods.HttpGet;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by lihuajun on 16-7-6.
 */
@Controller
@RequestMapping("/goods")
public class GoodsController extends BaseController {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private UserService userService;
    @Autowired
    private ConfigService configService;
    @Autowired
    private AppConfig appConfig;

    /**
     * 查找所有上架商品
     *
     * @return
     */
    @RequestMapping("/list")
    public ModelAndView list(String code) throws RequestException {

        ModelAndView modelAndView = new ModelAndView("home");

        //获取openid
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appConfig.wechatAppid + "&secret=" + appConfig.wechatSecret + "&code=" + code + "&grant_type=authorization_code";
        HttpGet httpGet = new HttpGet(url);
        JSONObject jsonObject = XHttpClient.doRequest(httpGet);
        modelAndView.addObject("openid", jsonObject.getString("openid"));
        logger.debug("openid={}", jsonObject.getString("openid"));
        //获取全部商品
        Goods goods = new Goods();
        goods.setStatus(1);
        modelAndView.addObject("goodsList", goodsService.listByCondition(goods));
        return modelAndView;
    }

    @RequestMapping("/get")
    public ModelAndView getGoods(Goods goods) {

        ModelAndView modelAndView = new ModelAndView("goods");
        modelAndView.addObject("goods", goodsService.selectByPrimaryKey(goods.getId()));
        modelAndView.addObject("effectiveTime", configService.getString("goods.effective.time"));
        return modelAndView;
    }

    @RequestMapping("/fix")
    public ModelAndView fix(Goods goods) {

        ModelAndView modelAndView = new ModelAndView("pay_fix");
        modelAndView.addObject("goods", goodsService.selectByPrimaryKey(goods.getId()));
        return modelAndView;
    }

    @RequestMapping("/order")
    public ModelAndView order(String openid, Integer goodsid) throws OrderException {
        ModelAndView modelAndView = new ModelAndView("order_success");

        User user = userService.getByOpenid(openid);
        Goods goods = goodsService.selectByPrimaryKey(goodsid);

        //VipAccount vipAccount = goodsService.order(user, goods);

        //modelAndView.addObject("vipAccount", vipAccount);
        return modelAndView;
    }
}
