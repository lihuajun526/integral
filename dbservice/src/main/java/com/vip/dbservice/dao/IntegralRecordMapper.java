package com.vip.dbservice.dao;

import com.vip.dbservice.model.IntegralRecord;

import java.util.Date;
import java.util.List;

public interface IntegralRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(IntegralRecord record);

    int insertSelective(IntegralRecord record);

    IntegralRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IntegralRecord record);

    int updateByPrimaryKey(IntegralRecord record);

    List<IntegralRecord> selectByBeginTime(Date startTime);

    IntegralRecord selectByCondition(IntegralRecord integralRecord);

    
}