package com.vip.integral.service;

import com.vip.integral.exception.OrderException;
import com.vip.integral.model.Goods;
import com.vip.integral.model.User;
import com.vip.integral.model.VipAccount;

import java.util.List;

/**
 * Created by lihuajun on 16-7-6.
 */
public interface GoodsService {

    // TODO: 16-7-6 查找所有上架商品 
    List<Goods> listAll(Integer status);

    Goods get(Goods goods);

    VipAccount order(User user, Goods goods) throws OrderException;

    int save(Goods goods);

    int update(Goods goods);

}
