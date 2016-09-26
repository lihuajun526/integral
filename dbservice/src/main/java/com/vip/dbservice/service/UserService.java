package com.vip.dbservice.service;

import com.vip.dbservice.model.User;
import com.vip.dbservice.model.IntegralRecord;

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

    List<User> getByIds(List<Integer> ids);

}
