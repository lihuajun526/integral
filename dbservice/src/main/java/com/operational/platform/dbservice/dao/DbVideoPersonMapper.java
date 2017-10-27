package com.operational.platform.dbservice.dao;

import com.operational.platform.dbservice.model.DbVideoPerson;
import com.operational.platform.dbservice.model.DbVideoPersonExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DbVideoPersonMapper {
    int countByExample(DbVideoPersonExample example);

    int deleteByExample(DbVideoPersonExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DbVideoPerson record);

    int insertSelective(DbVideoPerson record);

    List<DbVideoPerson> selectByExample(DbVideoPersonExample example);

    DbVideoPerson selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DbVideoPerson record, @Param("example") DbVideoPersonExample example);

    int updateByExample(@Param("record") DbVideoPerson record, @Param("example") DbVideoPersonExample example);

    int updateByPrimaryKeySelective(DbVideoPerson record);

    int updateByPrimaryKey(DbVideoPerson record);
}