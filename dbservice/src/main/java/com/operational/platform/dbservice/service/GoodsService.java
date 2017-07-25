package com.operational.platform.dbservice.service;

import com.operational.platform.dbservice.model.Goods;
import com.operational.platform.dbservice.model.User;
import com.operational.platform.dbservice.exception.OrderException;
import com.operational.platform.dbservice.model.VipAccount;

import java.util.List;

/**
 * Created by lihuajun on 16-7-6.
 */
public interface GoodsService {

    List<Goods> listByCondition(Goods goods);

    Goods selectByPrimaryKey(Integer id);

    void order(User user, Goods goods) throws OrderException;

    int save(Goods goods);

    int update(Goods goods);

}
