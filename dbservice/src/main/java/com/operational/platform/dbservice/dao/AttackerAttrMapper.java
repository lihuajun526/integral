package com.operational.platform.dbservice.dao;

import com.operational.platform.dbservice.model.AttackerAttr;
import com.operational.platform.dbservice.model.AttackerAttrExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AttackerAttrMapper {
    int countByExample(AttackerAttrExample example);

    int deleteByExample(AttackerAttrExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AttackerAttr record);

    int insertSelective(AttackerAttr record);

    List<AttackerAttr> selectByExampleWithBLOBs(AttackerAttrExample example);

    List<AttackerAttr> selectByExample(AttackerAttrExample example);

    AttackerAttr selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AttackerAttr record, @Param("example") AttackerAttrExample example);

    int updateByExampleWithBLOBs(@Param("record") AttackerAttr record, @Param("example") AttackerAttrExample example);

    int updateByExample(@Param("record") AttackerAttr record, @Param("example") AttackerAttrExample example);

    int updateByPrimaryKeySelective(AttackerAttr record);

    int updateByPrimaryKeyWithBLOBs(AttackerAttr record);

    int updateByPrimaryKey(AttackerAttr record);
}