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

    List<Goods> listByCondition(Goods goods);

    Goods get(Goods goods);

    VipAccount order(User user, Goods goods) throws OrderException;

    int save(Goods goods);

    int update(Goods goods);

}
