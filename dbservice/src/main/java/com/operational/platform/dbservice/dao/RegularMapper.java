package com.operational.platform.dbservice.dao;

import com.operational.platform.dbservice.model.Regular;
import com.operational.platform.dbservice.model.RegularExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RegularMapper {
    int countByExample(RegularExample example);

    int deleteByExample(RegularExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Regular record);

    int insertSelective(Regular record);

    List<Regular> selectByExample(RegularExample example);

    Regular selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Regular record, @Param("example") RegularExample example);

    int updateByExample(@Param("record") Regular record, @Param("example") RegularExample example);

    int updateByPrimaryKeySelective(Regular record);

    int updateByPrimaryKey(Regular record);
}