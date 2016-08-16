package com.vip.integral.service.impl;

import com.vip.integral.constant.ExceptionTypeEnum;
import com.vip.integral.exception.OrderException;
import com.vip.integral.model.Goods;
import com.vip.integral.model.IntegralRecord;
import com.vip.integral.model.User;
import com.vip.integral.model.VipAccount;
import com.vip.integral.service.ConfigService;
import com.vip.integral.service.GoodsService;
import com.vip.integral.service.VipAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

/**
 * Created by lihuajun on 2016/8/15.
 */
@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsService.class);

    @Autowired
    private ConfigService configService;
    @Autowired
    private VipAccountService vipAccountService;

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
    public VipAccount order(User user, Goods goods) throws OrderException {

        Calendar calendar = Calendar.getInstance();
        int curHour = calendar.get(Calendar.HOUR_OF_DAY);
        int curMinute = calendar.get(Calendar.MINUTE);
        if (curHour < configService.getInt("begin.sell") && curHour >= configService.getInt("end.sell")) {
            //不在开售时间范围
            LOGGER.warn("不在开售时间范围，用户[id={}]在{}点{}分试图买[title={}]的商品", user.getId(), curHour, curMinute, goods.getTitle());
            throw new OrderException(ExceptionTypeEnum.NOT_IN_SELL_TIME_ERROR);
        }
        //todo
        goods = null;
        if (goods.getCount() == 0) {
            //库存不足
            LOGGER.warn("库存不足，用户[id={}]在{}点{}分试图买[title={}]的商品", user.getId(), curHour, curMinute, goods.getTitle());
            throw new OrderException(ExceptionTypeEnum.STOCKS_LOW_ERROR);
        }

        //todo 用户积分余额是否足够
        if (user.getIntegral() < goods.getPrice()) {
            //账户余额不足
            LOGGER.warn("账户余额不足，用户[id={}]在{}点{}分试图买[title={}]的商品", user.getId(), curHour, curMinute, goods.getTitle());
            throw new OrderException(ExceptionTypeEnum.BALANCE_LOW_ERROR);
        }

        //todo 此商品用户今日是否买过
        calendar.set(Calendar.HOUR_OF_DAY, configService.getInt("begin.sell"));
        List<IntegralRecord> records = null;
        if (records != null && records.size() > 0) {
            //该用户今日已买过该商品，不能再次购买
            LOGGER.warn("不能再次购买，用户[id={}]在{}点{}分试图买[title={}]的商品", user.getId(), curHour, curMinute, goods.getTitle());
            throw new OrderException(ExceptionTypeEnum.TODAY_HAS_BUY_ERROR);
        }

        //todo 减库存
        goods.setCount(goods.getCount() - 1);
        VipAccount vipAccount = vipAccountService.vote(goods.getType());
        if (vipAccount.getCount() == 0) {
            LOGGER.error("VipAccount与Goods库存不同步，用户[id={}]在{}点{}分试图买[title={}]的商品", user.getId(), curHour, curMinute, goods.getTitle());
        } else {
            vipAccount.setCount(vipAccount.getCount() - 1);
            vipAccountService.update(vipAccount);
        }
        //todo 减积分
        user.setIntegral(user.getIntegral() - goods.getPrice());
        //todo 新增积分记录
        IntegralRecord integralRecord = new IntegralRecord();
        integralRecord.setType(20);//消费
        integralRecord.setCount(0 - goods.getPrice());
        integralRecord.setDesc("购买了商品[title=" + goods.getTitle() + "]");
        integralRecord.setUserid(user.getId());
        integralRecord.setGoodsid(goods.getId());
        integralRecord.setVipAccountId(vipAccount.getId());
        return vipAccount;
    }

    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();
        System.out.println(c.get(Calendar.HOUR_OF_DAY));
    }
}
