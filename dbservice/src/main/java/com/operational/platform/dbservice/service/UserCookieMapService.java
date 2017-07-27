package com.operational.platform.dbservice.service;

import com.operational.platform.dbservice.model.UserCookieMap;

import java.util.List;

/**
 * Created by lihuajun on 16-7-27.
 */
public interface UserCookieMapService {

    void save(UserCookieMap userCookieMap);

    void update(UserCookieMap userCookieMap);

    void del(Integer id);

    UserCookieMap getByUserAndBelong(Integer userid, String belong);

    void updateByUserAndCookie(UserCookieMap userCookieMap);

    List<UserCookieMap> listAll();

    void clearCookie(UserCookieMap userCookieMap);

}
