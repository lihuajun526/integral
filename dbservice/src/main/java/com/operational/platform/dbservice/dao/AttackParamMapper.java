package com.operational.platform.dbservice.dao;

import com.operational.platform.dbservice.model.AttackParam;
import com.operational.platform.dbservice.model.AttackParamExample;
import com.operational.platform.dbservice.model.AttackParamWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AttackParamMapper {
    int countByExample(AttackParamExample example);

    int deleteByExample(AttackParamExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AttackParamWithBLOBs record);

    int insertSelective(AttackParamWithBLOBs record);

    List<AttackParamWithBLOBs> selectByExampleWithBLOBs(AttackParamExample example);

    List<AttackParam> selectByExample(AttackParamExample example);

    AttackParamWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AttackParamWithBLOBs record, @Param("example") AttackParamExample example);

    int updateByExampleWithBLOBs(@Param("record") AttackParamWithBLOBs record, @Param("example") AttackParamExample example);

    int updateByExample(@Param("record") AttackParam record, @Param("example") AttackParamExample example);

    int updateByPrimaryKeySelective(AttackParamWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(AttackParamWithBLOBs record);

    int updateByPrimaryKey(AttackParam record);

	AttackParam getByNode(Integer nodeid);

    List<AttackParam> listByBelong(String belong);
}