package com.operational.platform.dbservice.dao;

import com.operational.platform.dbservice.model.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKeyWithBLOBs(User record);

    int updateByPrimaryKey(User record);

    User getByOpenid(String openid);

    List<User> getByIds(List<Integer> ids);
}