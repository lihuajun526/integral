package com.vip.integral.dao;

import com.vip.integral.model.AttackPage;

import java.util.List;

public interface AttackPageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AttackPage record);

    int insertSelective(AttackPage record);

    AttackPage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AttackPage record);

    int updateByPrimaryKey(AttackPage record);

    List<AttackPage> listByBelong(String belong);
}