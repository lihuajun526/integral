package com.vip.integral.controller;

import com.vip.integral.base.BaseController;
import com.vip.integral.base.Result;
import com.vip.integral.model.Goods;
import com.vip.integral.service.GoodsService;
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

}
