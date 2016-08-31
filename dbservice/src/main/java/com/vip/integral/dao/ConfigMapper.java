package com.vip.integral.dao;

import com.vip.integral.model.Config;

public interface ConfigMapper {
    int insert(Config record);

    int insertSelective(Config record);

    Config getByKey(String skey);
}