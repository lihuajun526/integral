package com.vip.dbservice.service.impl;

import com.vip.dbservice.constant.ExceptionTypeEnum;
import com.vip.dbservice.dao.IntegralRecordMapper;
import com.vip.dbservice.dao.UserMapper;
import com.vip.dbservice.exception.OrderException;
import com.vip.dbservice.model.Goods;
import com.vip.dbservice.model.User;
import com.vip.dbservice.service.ConfigService;
import com.vip.dbservice.service.GoodsService;
import com.vip.dbservice.service.VipAccountService;
import com.vip.dbservice.dao.GoodsMapper;
import com.vip.dbservice.model.IntegralRecord;
import com.vip.dbservice.model.VipAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IntegralRecordMapper integralRecordMapper;

    @Override
    public List<Goods> listByCondition(Goods goods) {
        return goodsMapper.listByCondition(goods);
    }

    @Override
    public Goods selectByPrimaryKey(Integer id) {
        return goodsMapper.selectByPrimaryKey(id);
    }

    /**
     * 下单
     *
     * @param user
     * @param goods
     * @return
     * @throws OrderException
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public VipAccount order(User user, Goods goods) throws OrderException {

        Calendar calendar = Calendar.getInstance();
        int curHour = calendar.get(Calendar.HOUR_OF_DAY);
        int curMinute = calendar.get(Calendar.MINUTE);
        if (curHour < configService.getInt("goods.begin.sell.time") && curHour >= configService.getInt("goods.end.sell.time")) {
            //不在开售时间范围
            LOGGER.warn("不在开售时间范围，用户[id={}]在{}点{}分试图买[title={}]的商品", user.getId(), curHour, curMinute, goods.getTitle());
            throw new OrderException(ExceptionTypeEnum.NOT_IN_SELL_TIME_ERROR);
        }
        if (goods.getCount() == 0) {
            //库存不足
            LOGGER.warn("库存不足，用户[id={}]在{}点{}分试图买[title={}]的商品", user.getId(), curHour, curMinute, goods.getTitle());
            throw new OrderException(ExceptionTypeEnum.STOCKS_LOW_ERROR);
        }

        //用户积分余额是否足够
        if (user.getIntegral() < goods.getPrice()) {
            //账户余额不足
            LOGGER.warn("账户余额不足，用户[id={}]在{}点{}分试图买[title={}]的商品", user.getId(), curHour, curMinute, goods.getTitle());
            throw new OrderException(ExceptionTypeEnum.BALANCE_LOW_ERROR);
        }

        //此商品用户今日是否买过
        calendar.set(Calendar.HOUR_OF_DAY, configService.getInt("goods.begin.sell.time"));

        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("startTime",calendar.getTime());
        paramMap.put("goodsid",goods.getId());
        List<IntegralRecord> records = integralRecordMapper.selectByBeginTime(paramMap);
        if (records != null && records.size() > 0) {
            //该用户今日已买过该商品，不能再次购买
            LOGGER.warn("不能再次购买，用户[id={}]在{}点{}分试图买[title={}]的商品", user.getId(), curHour, curMinute, goods.getTitle());
            throw new OrderException(ExceptionTypeEnum.TODAY_HAS_BUY_ERROR);
        }

        //减库存
        goods.setCount(goods.getCount() - 1);
        goodsMapper.updateByPrimaryKeySelective(goods);
        VipAccount vipAccount = vipAccountService.vote(goods.getVipType());
        if (vipAccount == null || vipAccount.getCount() == 0) {
            LOGGER.error("VipAccount与Goods库存不同步，用户[id={}]在{}点{}分试图买[title={}]的商品", user.getId(), curHour, curMinute, goods.getTitle());
            throw new OrderException(ExceptionTypeEnum.STOCK_NOT_SYN_ERROR);
        } else {
            vipAccount.setCount(vipAccount.getCount() - 1);
            vipAccountService.update(vipAccount);
        }
        //减积分
        user.setIntegral(user.getIntegral() - goods.getPrice());
        userMapper.updateByPrimaryKeySelective(user);
        //新增积分记录
        IntegralRecord integralRecord = new IntegralRecord();
        integralRecord.setType(20);//消费
        integralRecord.setCount(0 - goods.getPrice());
        integralRecord.setDes("购买了商品[title=" + goods.getTitle() + "]");
        integralRecord.setUserid(user.getId());
        integralRecord.setGoodsid(goods.getId());
        integralRecord.setVipAccountId(vipAccount.getId());
        integralRecordMapper.insert(integralRecord);
        return vipAccount;
    }

    @Override
    public int save(Goods goods) {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        goods.setEffectiveTime(calendar.getTime());
        return goodsMapper.insert(goods);
    }

    @Override
    public int update(Goods goods) {
        return goodsMapper.updateByPrimaryKeySelective(goods);
    }

}
