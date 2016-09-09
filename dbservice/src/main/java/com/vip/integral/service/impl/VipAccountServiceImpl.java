package com.vip.integral.service.impl;

import com.vip.integral.dao.VipAccountMapper;
import com.vip.integral.model.User;
import com.vip.integral.model.VipAccount;
import com.vip.integral.service.ConfigService;
import com.vip.integral.service.VipAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by lihuajun on 2016/8/16.
 */
@Service("vipAccountService")
public class VipAccountServiceImpl implements VipAccountService {

    @Autowired
    private VipAccountMapper vipAccountMapper;
    @Autowired
    private ConfigService configService;

    @Override
    public VipAccount vote(Integer type) {
        List<VipAccount> list = vipAccountMapper.listByTypeOrderByCountDesc(type);
        if (list == null)
            return null;
        VipAccount vipAccount = list.get(0);
        if (vipAccount.getCount() == 0)
            return null;
        return vipAccount;
    }

    @Override
    public int update(VipAccount vipAccount) {
        return vipAccountMapper.updateByPrimaryKeySelective(vipAccount);
    }

    @Override
    public int save(VipAccount vipAccount) {
        return vipAccountMapper.insert(vipAccount);
    }

    @Override
    public VipAccount get(VipAccount vipAccount) {
        return vipAccountMapper.selectByPrimaryKey(vipAccount.getId());
    }

    @Override
    public List<VipAccount> listAll() {
        return vipAccountMapper.listAll();
    }

    @Override
    public int delete(VipAccount vipAccount) {
        return vipAccountMapper.deleteByPrimaryKey(vipAccount.getId());
    }

    @Override
    public List<VipAccount> listVip(User user) {

        //初始化
        int beginSellTime = configService.getInt("goods.begin.sell.time");
        int effectiveTime = configService.getInt("goods.effective.time");
        int endSellTime = configService.getInt("goods.end.sell.time");

        Calendar calendar = Calendar.getInstance();
        int curHour = calendar.get(Calendar.HOUR_OF_DAY);

        if (curHour >= beginSellTime) {
            //获取大于等于今日开卖点买的订单
            calendar.set(Calendar.HOUR_OF_DAY, beginSellTime);
            Date startTime = calendar.getTime();

            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("userid", user.getId());
            paramMap.put("startTime", startTime);

            return vipAccountMapper.listByUserAndTime(paramMap);
        } else if (curHour < effectiveTime) {
            //获取昨天开买点到今日结束点购买的订单
            calendar.set(Calendar.HOUR_OF_DAY, endSellTime);
            Date endTime = calendar.getTime();

            calendar.add(Calendar.DATE, -1);
            calendar.set(Calendar.HOUR_OF_DAY, beginSellTime);
            Date startTime = calendar.getTime();

            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("userid", user.getId());
            paramMap.put("startTime", startTime);
            paramMap.put("endTime", endTime);

            return vipAccountMapper.listByUserAndTime(paramMap);
        }
        return null;
    }
}
