package com.operational.platform.dbservice.dao;

import com.operational.platform.dbservice.model.EmailAccount;
import com.operational.platform.dbservice.model.EmailAccountExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface EmailAccountMapper {
    int countByExample(EmailAccountExample example);

    int deleteByExample(EmailAccountExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EmailAccount record);

    int insertSelective(EmailAccount record);

    List<EmailAccount> selectByExample(EmailAccountExample example);

    EmailAccount selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EmailAccount record, @Param("example") EmailAccountExample example);

    int updateByExample(@Param("record") EmailAccount record, @Param("example") EmailAccountExample example);

    int updateByPrimaryKeySelective(EmailAccount record);

    int updateByPrimaryKey(EmailAccount record);

    List<EmailAccount> listByPage(Map<String, Integer> condition);

    int countByPage();
}