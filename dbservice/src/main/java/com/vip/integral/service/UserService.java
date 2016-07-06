package com.vip.integral.service;

import com.vip.integral.model.User;

/**
 * Created by lihuajun on 16-7-6.
 */
public interface UserService {

    User getByOpenid(String openid);

}
