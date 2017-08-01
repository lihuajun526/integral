package com.operational.platform.vip.controller;

import com.operational.platform.common.constant.ExceptionCode;
import com.operational.platform.dbservice.exception.OrderException;
import com.operational.platform.dbservice.model.Goods;
import com.operational.platform.dbservice.model.User;
import com.operational.platform.dbservice.service.ConfigService;
import com.operational.platform.dbservice.service.GoodsService;
import com.operational.platform.dbservice.service.UserService;
import com.operational.platform.vip.base.BaseController;
import com.operational.platform.vip.base.Result;
import com.operational.platform.vip.constant.Constant;
import com.operational.platform.vip.service.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @ResponseBody
    public String list() {

        Result<List<Goods>> result = new Result<>();

        //获取全部商品
        Goods goods = new Goods();
        goods.setStatus(1);

        List<Goods> list = goodsService.listByCondition(goods);
        result.setData(list);

        return result.toString();
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
    @ResponseBody
    public String order(Integer goodsid, String vipAccessToken) {

        Result<Map<String,Object>> result = new Result();
        Map<String, Object> map = new HashMap<>();

        User loginUser = userService.getByAccessToken(vipAccessToken);
        Goods goods = goodsService.selectByPrimaryKey(goodsid);
        try {
            goodsService.order(loginUser, goods);
        } catch (OrderException e) {
            logger.error("错误编码：{}，错误描述：{}", e.getCode(), e.getDescription());
            result.setCode(ExceptionCode.INTEGRAL_NOT_ENOUGH_ERROR.code);
            result.setMessage("对不起，您的余额不足，请充值");
            return result.toString();
        }

        map.put("vipExpires", loginUser.getVipExpires());
        result.setData(map);
        result.setMessage("购买成功");
        return result.toString();
    }
}
