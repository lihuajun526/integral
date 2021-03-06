package com.operational.platform.dbservice.service;

import com.operational.platform.dbservice.model.IntegralRecord;
import com.operational.platform.dbservice.model.User;

import java.util.Date;
import java.util.List;

/**
 * Created by lihuajun on 16-7-6.
 */
public interface UserService {

    User getByOpenid(String openid);

    /**
     * 奖励积分
     * @param user
     * @param integralRecord
     */
    void encourageIntegral(User user, IntegralRecord integralRecord);

    List<User> listSpreads(Integer userid);

    int saveUserAndRecord(User user,IntegralRecord integralRecord);

    int update(User user);

    List<User> getByIds(List<Integer> ids);

    User getByUnionid(String unionid);

    User getByAccessToken(String accessToken);

    List<User> listIntegralNotEnough();

    Date encourageFromShare(User user,int days);

    User get(Integer id);

}
