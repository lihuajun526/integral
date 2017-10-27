package com.operational.platform.dbservice.dao;

import com.operational.platform.dbservice.model.DbVideoTag;
import com.operational.platform.dbservice.model.DbVideoTagExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DbVideoTagMapper {
    int countByExample(DbVideoTagExample example);

    int deleteByExample(DbVideoTagExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DbVideoTag record);

    int insertSelective(DbVideoTag record);

    List<DbVideoTag> selectByExample(DbVideoTagExample example);

    DbVideoTag selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DbVideoTag record, @Param("example") DbVideoTagExample example);

    int updateByExample(@Param("record") DbVideoTag record, @Param("example") DbVideoTagExample example);

    int updateByPrimaryKeySelective(DbVideoTag record);

    int updateByPrimaryKey(DbVideoTag record);
}