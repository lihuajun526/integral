package com.vip.integral.dao;

import com.vip.integral.model.VipAccount;

public interface VipAccountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VipAccount record);

    int insertSelective(VipAccount record);

    VipAccount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VipAccount record);

    int updateByPrimaryKey(VipAccount record);
}