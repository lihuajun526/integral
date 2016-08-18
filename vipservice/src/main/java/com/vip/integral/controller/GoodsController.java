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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by lihuajun on 16-7-6.
 */
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
    @ResponseBody
    @RequestMapping("/list")
    public String list() {
        Result<List<Goods>> result = new Result<>();

        result.set(0, goodsService.listAll());

        return result.toString();
    }

    @ResponseBody
    @RequestMapping("/get")
    public String getGoods(Goods goods) {
        Result<Goods> result = new Result<>();

        result.set(0, goodsService.get(goods));

        return result.toString();
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
