package com.operational.platform.dbservice.dao;

import com.operational.platform.dbservice.model.AttackTask;
import com.operational.platform.dbservice.model.AttackTaskExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface AttackTaskMapper {
    int countByExample(AttackTaskExample example);

    int deleteByExample(AttackTaskExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AttackTask record);

    int insertSelective(AttackTask record);

    List<AttackTask> selectByExampleWithBLOBs(AttackTaskExample example);

    List<AttackTask> selectByExample(AttackTaskExample example);

    AttackTask selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AttackTask record, @Param("example") AttackTaskExample example);

    int updateByExampleWithBLOBs(@Param("record") AttackTask record, @Param("example") AttackTaskExample example);

    int updateByExample(@Param("record") AttackTask record, @Param("example") AttackTaskExample example);

    int updateByPrimaryKeySelective(AttackTask record);

    int updateByPrimaryKeyWithBLOBs(AttackTask record);

    int updateByPrimaryKey(AttackTask record);

    List<AttackTask> listByBelongAndStatus(Map<String, Object> map);
}