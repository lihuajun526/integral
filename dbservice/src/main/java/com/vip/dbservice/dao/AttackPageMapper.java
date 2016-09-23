package com.vip.dbservice.dao;

import com.vip.dbservice.model.AttackPage;

import java.util.List;

public interface AttackPageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AttackPage record);

    int insertSelective(AttackPage record);

    AttackPage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AttackPage record);

    int updateByPrimaryKey(AttackPage record);

    List<AttackPage> listByBelong(String belong);

    List<AttackPage> listByCondition(AttackPage attackPage);
}