package com.operational.platform.dbservice.service.impl;

import com.operational.platform.dbservice.dao.UserMapper;
import com.operational.platform.dbservice.model.IntegralRecord;
import com.operational.platform.dbservice.model.User;
import com.operational.platform.dbservice.model.UserExample;
import com.operational.platform.dbservice.model.UserTask;
import com.operational.platform.dbservice.service.IntegralRecordService;
import com.operational.platform.dbservice.service.IntegralService;
import com.operational.platform.dbservice.service.UserService;
import com.operational.platform.dbservice.service.UserTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by lihuajun on 2016/8/15.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IntegralService integralService;
    @Autowired
    private IntegralRecordService integralRecordService;
    @Autowired
    private UserTaskService userTaskService;

    @Override
    public User getByOpenid(String openid) {
        return userMapper.getByOpenid(openid);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void encourageIntegral(User user, IntegralRecord integralRecord) {
        user.setIntegral(user.getIntegral().intValue() + integralRecord.getIntegral().intValue());
        userMapper.updateByPrimaryKeySelective(user);
        integralRecordService.save(integralRecord);
    }


    @Override
    public List<User> listSpreads(Integer userid) {

        List<User> list = new ArrayList<>();

        User user = null;
        //id1:status#id2:status
        String[] datas = user.getSpreadRecord().split("#");
        StringBuffer ids = new StringBuffer();
        boolean isFirst = true;
        for (String str : datas) {
            String[] values = str.split(":");
            if ("1".equals(values[1])) {
                if (isFirst) {
                    ids.append(values[0]);
                    isFirst = false;
                    continue;
                }
                ids.append(",").append(values[0]);
            }
        }

        if (StringUtils.isEmpty(ids.toString()))
            return list;

        //todo
        list = null;

        return list;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int save(User user) {
        int i = userMapper.insert(user);
        IntegralRecord integralRecord = new IntegralRecord();
        integralRecord.setUserid(user.getId());
        integralRecord.setDescription("新用户注册");
        integralRecord.setGoodsid(0);
        integralRecord.setType(14);
        integralRecordService.save(integralRecord);
        return i;
    }

    @Override
    public int update(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public List<User> getByIds(List<Integer> ids) {
        return userMapper.getByIds(ids);
    }

    @Override
    public User getByUnionid(String unionid) {
        return userMapper.getByUnionid(unionid);
    }

    @Override
    public User getByAccessToken(String accessToken) {

        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andVipAccessTokenEqualTo(accessToken);

        List<User> list = userMapper.selectByExample(example);
        if (list.size() == 0)
            return null;
        return list.get(0);
    }

    @Override
    public List<User> listIntegralNotEnough() {

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DAY_OF_YEAR, 1);

        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andIntegralLessThan(100);
        criteria.andVipExpiresLessThan(c.getTime());

        return userMapper.selectByExample(example);
    }

    @Override
    public Date encourageFromShare(User user, int days) {
        if (userTaskService.listByUserAndType(user.getId(), 2).size() > 0) {//只有第一次分享才奖励
            return null;
        }
        //延迟会员期限
        Calendar c = Calendar.getInstance();
        if (user.getVipExpires().getTime() > System.currentTimeMillis())
            c.setTime(user.getVipExpires());
        else
            c.setTime(new Date());
        c.add(Calendar.DAY_OF_YEAR, days);
        user.setVipExpires(c.getTime());
        userMapper.updateByPrimaryKeySelective(user);
        //添加任务
        UserTask userTask = new UserTask();
        userTask.setType(2);
        userTask.setUserid(user.getId());
        userTask.setDescription("分享朋友圈奖励" + days + "天会员权益");
        userTaskService.save(userTask);
        return user.getVipExpires();
    }

    @Override
    public User get(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

}
