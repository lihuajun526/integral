package com.vip.dbservice.service;

import com.vip.dbservice.exception.OrderException;
import com.vip.dbservice.model.User;
import com.vip.dbservice.model.Goods;
import com.vip.dbservice.model.VipAccount;

import java.util.List;

/**
 * Created by lihuajun on 16-7-6.
 */
public interface GoodsService {

    List<Goods> listByCondition(Goods goods);

    Goods selectByPrimaryKey(Integer id);

    VipAccount order(User user, Goods goods) throws OrderException;

    int save(Goods goods);

    int update(Goods goods);

}
