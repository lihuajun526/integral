package com.operational.platform.dbservice.dao;

import com.operational.platform.dbservice.model.Config;

public interface ConfigMapper {
    int insert(Config record);

    int insertSelective(Config record);

    Config getByKey(String skey);
}