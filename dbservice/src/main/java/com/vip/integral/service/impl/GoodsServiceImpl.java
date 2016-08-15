package com.vip.integral.service.impl;

import com.vip.integral.model.Goods;
import com.vip.integral.service.ConfigService;
import com.vip.integral.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

/**
 * Created by lihuajun on 2016/8/15.
 */
@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private ConfigService configService;

    @Override
    public List<Goods> listAll() {
        return null;
    }

    @Override
    public Goods get(Goods goods) {
        return null;
    }

    //todo 事务
    @Override
    public Integer order(Integer userid, Goods goods) {

        Calendar c = Calendar.getInstance();
        int curHour = c.get(Calendar.HOUR_OF_DAY);
        if (curHour < configService.getInt("begin.sell") && curHour >= configService.getInt("end.sell")) {
            //不在开售时间范围
            return 10;
        }
        //todo
        goods = null;
        if (goods.getCount() == 0) {
            //库存不足
            return 20;
        }

        //todo 此商品用户今日是否买过
        if (true) {
            //该用户今日已买过该商品，不能再次购买
            return 30;
        }

        //todo 减库存
        //todo 减积分
        //todo 新增积分记录
        return 0;
    }

    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();
        System.out.println(c.get(Calendar.HOUR_OF_DAY));
    }
}
