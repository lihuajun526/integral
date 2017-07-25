package com.operational.platform.dbservice.dao;

import com.operational.platform.dbservice.model.AttackPage;
import com.operational.platform.dbservice.model.AttackPageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AttackPageMapper {
    int countByExample(AttackPageExample example);

    int deleteByExample(AttackPageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AttackPage record);

    int insertSelective(AttackPage record);

    List<AttackPage> selectByExampleWithBLOBs(AttackPageExample example);

    List<AttackPage> selectByExample(AttackPageExample example);

    AttackPage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AttackPage record, @Param("example") AttackPageExample example);

    int updateByExampleWithBLOBs(@Param("record") AttackPage record, @Param("example") AttackPageExample example);

    int updateByExample(@Param("record") AttackPage record, @Param("example") AttackPageExample example);

    int updateByPrimaryKeySelective(AttackPage record);

    int updateByPrimaryKeyWithBLOBs(AttackPage record);

    int updateByPrimaryKey(AttackPage record);

    List<AttackPage> listByBelong(String belong);

    List<AttackPage> listByCondition(AttackPage attackPage);
}