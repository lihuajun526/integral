package com.operational.platform.dbservice.service.impl;

import com.operational.platform.dbservice.constant.ExceptionTypeEnum;
import com.operational.platform.dbservice.dao.GoodsMapper;
import com.operational.platform.dbservice.dao.IntegralRecordMapper;
import com.operational.platform.dbservice.exception.OrderException;
import com.operational.platform.dbservice.model.Goods;
import com.operational.platform.dbservice.model.IntegralRecord;
import com.operational.platform.dbservice.model.User;
import com.operational.platform.dbservice.model.VipAccount;
import com.operational.platform.dbservice.service.ConfigService;
import com.operational.platform.dbservice.service.GoodsService;
import com.operational.platform.dbservice.service.VipAccountService;
import com.operational.platform.dbservice.dao.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    public void order(User user, Goods goods) throws OrderException {

        Calendar calendar = Calendar.getInstance();
        //用户积分余额是否足够
        if (user.getIntegral() < goods.getPrice()) {
            //账户余额不足
            LOGGER.warn("账户余额不足，用户[id={}]试图买[title={}]的商品", user.getId(), goods.getTitle());
            throw new OrderException(ExceptionTypeEnum.BALANCE_LOW_ERROR);
        }
        //减积分，加会员权益期限
        user.setIntegral(user.getIntegral() - goods.getPrice());
        if (user.getVipExpires() == null || user.getVipExpires().getTime() < System.currentTimeMillis())
            user.setVipExpires(new Date());
        calendar.setTime(user.getVipExpires());
        calendar.add(Calendar.DAY_OF_YEAR, goods.getDays());
        user.setVipExpires(calendar.getTime());
        userMapper.updateByPrimaryKeySelective(user);
        //新增积分记录
        IntegralRecord integralRecord = new IntegralRecord();
        integralRecord.setType(20);//消费
        integralRecord.setCount(0 - goods.getPrice());
        integralRecord.setDes("购买了商品[title=" + goods.getTitle() + "]");
        integralRecord.setUserid(user.getId());
        integralRecord.setGoodsid(goods.getId());
        integralRecordMapper.insert(integralRecord);
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
