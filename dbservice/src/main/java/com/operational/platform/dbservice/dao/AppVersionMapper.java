package com.operational.platform.dbservice.dao;

import com.operational.platform.dbservice.model.AppVersion;
import com.operational.platform.dbservice.model.AppVersionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppVersionMapper {
    int countByExample(AppVersionExample example);

    int deleteByExample(AppVersionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AppVersion record);

    int insertSelective(AppVersion record);

    List<AppVersion> selectByExample(AppVersionExample example);

    AppVersion selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AppVersion record, @Param("example") AppVersionExample example);

    int updateByExample(@Param("record") AppVersion record, @Param("example") AppVersionExample example);

    int updateByPrimaryKeySelective(AppVersion record);

    int updateByPrimaryKey(AppVersion record);
}