package com.operational.platform.dbservice.dao;

import com.operational.platform.dbservice.model.DbVideoImage;
import com.operational.platform.dbservice.model.DbVideoImageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DbVideoImageMapper {
    int countByExample(DbVideoImageExample example);

    int deleteByExample(DbVideoImageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DbVideoImage record);

    int insertSelective(DbVideoImage record);

    List<DbVideoImage> selectByExample(DbVideoImageExample example);

    DbVideoImage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DbVideoImage record, @Param("example") DbVideoImageExample example);

    int updateByExample(@Param("record") DbVideoImage record, @Param("example") DbVideoImageExample example);

    int updateByPrimaryKeySelective(DbVideoImage record);

    int updateByPrimaryKey(DbVideoImage record);
}