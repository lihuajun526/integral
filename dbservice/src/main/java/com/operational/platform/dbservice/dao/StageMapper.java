package com.operational.platform.dbservice.dao;

import com.operational.platform.dbservice.model.Stage;
import com.operational.platform.dbservice.model.StageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StageMapper {
    int countByExample(StageExample example);

    int deleteByExample(StageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Stage record);

    int insertSelective(Stage record);

    List<Stage> selectByExample(StageExample example);

    Stage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Stage record, @Param("example") StageExample example);

    int updateByExample(@Param("record") Stage record, @Param("example") StageExample example);

    int updateByPrimaryKeySelective(Stage record);

    int updateByPrimaryKey(Stage record);
}