package com.vip.integral.service.impl;

import com.vip.integral.model.IntegralRecord;
import com.vip.integral.model.User;
import com.vip.integral.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihuajun on 2016/8/15.
 */
public class UserServiceImpl implements UserService {


    @Override
    public User getByOpenid(String openid) {
        return null;
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

}
