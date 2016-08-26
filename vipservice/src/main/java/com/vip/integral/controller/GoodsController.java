package com.vip.integral.controller;

import com.vip.integral.base.BaseController;
import com.vip.integral.base.Result;
import com.vip.integral.exception.OrderException;
import com.vip.integral.model.Goods;
import com.vip.integral.model.User;
import com.vip.integral.model.VipAccount;
import com.vip.integral.service.GoodsService;
import com.vip.integral.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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

    /**
     * 查找所有上架商品
     *
     * @return
     */
    @RequestMapping("/list")
    public ModelAndView list() {

        ModelAndView modelAndView = new ModelAndView("home");
        Goods goods = new Goods();
        goods.setStatus(1);
        modelAndView.addObject("goodsList", goodsService.listByCondition(goods));
        return modelAndView;
    }

    @RequestMapping("/get")
    public ModelAndView getGoods(Goods goods) {

        ModelAndView modelAndView = new ModelAndView("goods");
        modelAndView.addObject("goods", goodsService.get(goods));
        return modelAndView;
    }

    @RequestMapping("/fix")
    public ModelAndView fix(Goods goods) {

        ModelAndView modelAndView = new ModelAndView("pay_fix");
        modelAndView.addObject("goods", goodsService.get(goods));
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("/order")
    public String order(User user, Goods goods) {
        Result<VipAccount> result = new Result<>();

        try {
            result.set(0, goodsService.order(user, goods));
        } catch (OrderException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

}
