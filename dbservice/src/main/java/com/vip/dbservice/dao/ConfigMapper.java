package com.vip.dbservice.dao;

import com.vip.dbservice.model.Config;

public interface ConfigMapper {
    int insert(Config record);

    int insertSelective(Config record);

    Config getByKey(String skey);
}