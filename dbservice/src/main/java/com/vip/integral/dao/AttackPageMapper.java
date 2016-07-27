package com.vip.integral.dao;

import com.vip.integral.model.AttackPage;

public interface AttackPageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AttackPage record);

    int insertSelective(AttackPage record);

    AttackPage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AttackPage record);

    int updateByPrimaryKey(AttackPage record);
}