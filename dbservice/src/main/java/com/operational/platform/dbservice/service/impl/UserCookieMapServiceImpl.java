package com.operational.platform.dbservice.service.impl;

import com.operational.platform.dbservice.dao.AttackParamMapper;
import com.operational.platform.dbservice.dao.UserCookieMapMapper;
import com.operational.platform.dbservice.model.AttackParam;
import com.operational.platform.dbservice.model.AttackParamWithBLOBs;
import com.operational.platform.dbservice.model.UserCookieMap;
import com.operational.platform.dbservice.model.UserCookieMapExample;
import com.operational.platform.dbservice.service.UserCookieMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lihuajun on 2017/7/26.
 */
@Service("userCookieMap")
public class UserCookieMapServiceImpl implements UserCookieMapService {

    @Autowired
    private UserCookieMapMapper userCookieMapMapper;
    @Autowired
    private AttackParamMapper attackParamMapper;

    @Override
    public void save(UserCookieMap userCookieMap) {
        userCookieMapMapper.insert(userCookieMap);
    }

    @Override
    public void update(UserCookieMap userCookieMap) {
        userCookieMapMapper.updateByPrimaryKeySelective(userCookieMap);
    }

    @Override
    public void del(Integer id) {
        userCookieMapMapper.deleteByPrimaryKey(id);
    }

    @Override
    public UserCookieMap getByUserAndBelong(Integer userid, String belong) {
        UserCookieMapExample userCookieMapExample = new UserCookieMapExample();
        UserCookieMapExample.Criteria criteria = userCookieMapExample.createCriteria();
        criteria.andUseridEqualTo(userid);
        criteria.andBelongEqualTo(belong);
        List<UserCookieMap> list = userCookieMapMapper.selectByExample(userCookieMapExample);
        if (list.size() > 0)
            return list.get(0);
        return null;
    }

    @Override
    public void updateByUserAndCookie(UserCookieMap userCookieMap) {
        UserCookieMapExample userCookieMapExample = new UserCookieMapExample();
        UserCookieMapExample.Criteria criteria = userCookieMapExample.createCriteria();
        criteria.andUseridEqualTo(userCookieMap.getUserid());
        criteria.andCookieidEqualTo(userCookieMap.getCookieid());
        userCookieMapMapper.updateByExampleSelective(userCookieMap, userCookieMapExample);
    }

    @Override
    public List<UserCookieMap> listAll() {
        return userCookieMapMapper.selectByExample(new UserCookieMapExample());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackForClassName = "Exception")
    public void clearCookie(UserCookieMap userCookieMap) {

        AttackParam attackParam = attackParamMapper.selectByPrimaryKey(userCookieMap.getCookieid());
        if (attackParam.getNum().intValue() > 0) {
            AttackParamWithBLOBs attackParamWithBLOBs = new AttackParamWithBLOBs();
            attackParamWithBLOBs.setId(attackParam.getId());
            attackParamWithBLOBs.setNum(attackParam.getNum() - 1);
            attackParamMapper.updateByPrimaryKeySelective(attackParamWithBLOBs);
        }
        userCookieMapMapper.deleteByPrimaryKey(userCookieMap.getId());
    }
}
