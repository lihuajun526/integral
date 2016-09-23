package com.vip.dbservice.dao;

import com.vip.dbservice.model.Goods;

import java.util.List;

public interface GoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    List<Goods> listByCondition(Goods goods);

    Goods selectByTypeAndVipType(Goods goods);
}