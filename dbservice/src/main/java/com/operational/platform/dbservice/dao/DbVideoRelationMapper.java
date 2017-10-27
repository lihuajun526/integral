package com.operational.platform.dbservice.dao;

import com.operational.platform.dbservice.model.DbVideoRelation;
import com.operational.platform.dbservice.model.DbVideoRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DbVideoRelationMapper {
    int countByExample(DbVideoRelationExample example);

    int deleteByExample(DbVideoRelationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DbVideoRelation record);

    int insertSelective(DbVideoRelation record);

    List<DbVideoRelation> selectByExample(DbVideoRelationExample example);

    DbVideoRelation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DbVideoRelation record, @Param("example") DbVideoRelationExample example);

    int updateByExample(@Param("record") DbVideoRelation record, @Param("example") DbVideoRelationExample example);

    int updateByPrimaryKeySelective(DbVideoRelation record);

    int updateByPrimaryKey(DbVideoRelation record);
}