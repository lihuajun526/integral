package com.vip.integral.dao;

import com.vip.dbservice.model.IntegralRecord;

public interface IntegralRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(IntegralRecord record);

    int insertSelective(IntegralRecord record);

    IntegralRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IntegralRecord record);

    int updateByPrimaryKey(IntegralRecord record);
}