package com.vip.dbservice.service.impl;

import com.vip.dbservice.dao.ConfigMapper;
import com.vip.dbservice.service.ConfigService;
import com.vip.dbservice.model.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lihuajun on 2016/8/15.
 */
@Service("configService")
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private ConfigMapper configMapper;

    @Override
    public String getString(String skey) {
        Config config = configMapper.getByKey(skey);
        return config.getValue();
    }

    @Override
    public Integer getInt(String skey) {
        Config config = configMapper.getByKey(skey);
        return Integer.valueOf(config.getValue());
    }

    @Override
    public Long getLong(String skey) {
        Config config = configMapper.getByKey(skey);
        return Long.valueOf(config.getValue());
    }
}
