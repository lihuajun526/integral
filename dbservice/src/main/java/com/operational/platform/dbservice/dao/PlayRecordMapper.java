package com.operational.platform.dbservice.dao;

import com.operational.platform.dbservice.model.PlayRecord;
import com.operational.platform.dbservice.model.PlayRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PlayRecordMapper {
    int countByExample(PlayRecordExample example);

    int deleteByExample(PlayRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PlayRecord record);

    int insertSelective(PlayRecord record);

    List<PlayRecord> selectByExample(PlayRecordExample example);

    PlayRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PlayRecord record, @Param("example") PlayRecordExample example);

    int updateByExample(@Param("record") PlayRecord record, @Param("example") PlayRecordExample example);

    int updateByPrimaryKeySelective(PlayRecord record);

    int updateByPrimaryKey(PlayRecord record);
}