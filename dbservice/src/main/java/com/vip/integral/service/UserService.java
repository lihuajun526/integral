package com.vip.integral.service;

import com.vip.integral.model.IntegralRecord;
import com.vip.integral.model.User;

import java.util.List;

/**
 * Created by lihuajun on 16-7-6.
 */
public interface UserService {

    User getByOpenid(String openid);

    /**
     * 奖励积分
     *
     * @param userid
     * @param integralRecord
     * @return
     */
    Boolean encourageIntegral(Integer userid, IntegralRecord integralRecord);

    List<User> listSpreads(Integer userid);

    int save(User user);

    int update(User user);

}
