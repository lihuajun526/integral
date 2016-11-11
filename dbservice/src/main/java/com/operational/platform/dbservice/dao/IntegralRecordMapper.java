package com.operational.platform.dbservice.dao;

import com.operational.platform.dbservice.model.IntegralRecord;

import java.util.List;
import java.util.Map;

public interface IntegralRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(IntegralRecord record);

    int insertSelective(IntegralRecord record);

    IntegralRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IntegralRecord record);

    int updateByPrimaryKey(IntegralRecord record);

    List<IntegralRecord> selectByBeginTime(Map<String,Object> paramMap);

    IntegralRecord selectByCondition(IntegralRecord integralRecord);

    List<IntegralRecord> selectBySelective(IntegralRecord integralRecord);

}