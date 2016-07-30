package com.vip.integral.dao;

import com.vip.integral.model.AttackParam;

import java.util.List;

public interface AttackParamMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AttackParam record);

    int insertSelective(AttackParam record);

    AttackParam selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AttackParam record);

    int updateByPrimaryKeyWithBLOBs(AttackParam record);

    int updateByPrimaryKey(AttackParam record);

    AttackParam getByNode(Integer nodeid);

    List<AttackParam> listByBelong(String belong);
}