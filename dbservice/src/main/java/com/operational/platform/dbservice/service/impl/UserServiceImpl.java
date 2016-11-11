package com.operational.platform.dbservice.service.impl;

import com.operational.platform.dbservice.model.IntegralRecord;
import com.operational.platform.dbservice.model.User;
import com.operational.platform.dbservice.service.UserService;
import com.operational.platform.dbservice.dao.UserMapper;
import com.operational.platform.dbservice.service.IntegralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
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

    @Override
    public User getByOpenid(String openid) {
        return userMapper.getByOpenid(openid);
    }

    @Override
    public Boolean encourageIntegral(Integer userid, IntegralRecord integralRecord) {


        return null;
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

    @Override
    public int save(User user) {
        int i = userMapper.insert(user);
        //奖励积分
        integralService.encourageFromFocus(user.getId(), user.getIntegral());
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

}
