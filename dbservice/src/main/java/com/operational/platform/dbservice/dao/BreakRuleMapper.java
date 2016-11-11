package com.operational.platform.dbservice.dao;


import com.operational.platform.dbservice.model.BreakRule;

import java.util.List;

public interface BreakRuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BreakRule record);

    int insertSelective(BreakRule record);

    BreakRule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BreakRule record);

    int updateByPrimaryKey(BreakRule record);

    List<BreakRule> selectByCrawlOrderByLevel(Integer crawlId);
}