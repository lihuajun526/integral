package com.operational.platform.dbservice.dao;

import com.operational.platform.dbservice.model.IntegralRecord;
import com.operational.platform.dbservice.model.IntegralRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IntegralRecordMapper {
    int countByExample(IntegralRecordExample example);

    int deleteByExample(IntegralRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IntegralRecord record);

    int insertSelective(IntegralRecord record);

    List<IntegralRecord> selectByExample(IntegralRecordExample example);

    IntegralRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IntegralRecord record, @Param("example") IntegralRecordExample example);

    int updateByExample(@Param("record") IntegralRecord record, @Param("example") IntegralRecordExample example);

    int updateByPrimaryKeySelective(IntegralRecord record);

    int updateByPrimaryKey(IntegralRecord record);

    IntegralRecord selectByCondition(IntegralRecord integralRecord);

    List<IntegralRecord> selectBySelective(IntegralRecord integralRecord);
}