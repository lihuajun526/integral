package com.operational.platform.dbservice.dao;

import com.operational.platform.dbservice.model.VipAccount;

import java.util.List;
import java.util.Map;

public interface VipAccountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VipAccount record);

    int insertSelective(VipAccount record);

    VipAccount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VipAccount record);

    int updateByPrimaryKey(VipAccount record);

    List<VipAccount> listAll();

    List<VipAccount> listByTypeOrderByCountDesc(Integer type);

    List<VipAccount> listByUserAndTime(Map paramMap);
}