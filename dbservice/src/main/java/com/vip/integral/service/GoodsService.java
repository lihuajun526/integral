package com.vip.integral.service;

import com.vip.integral.model.Goods;

import java.util.List;

/**
 * Created by lihuajun on 16-7-6.
 */
public interface GoodsService {

    // TODO: 16-7-6 查找所有上架商品 
    List<Goods> listAll();

    // TODO: 16-7-6 跟据id查找商品，且status=1 
    Goods get(Goods goods);
    
}