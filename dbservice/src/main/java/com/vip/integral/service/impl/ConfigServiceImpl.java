package com.vip.integral.service.impl;

import com.vip.integral.dao.ConfigMapper;
import com.vip.integral.model.Config;
import com.vip.integral.service.ConfigService;
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
    public String getString(String key) {
        Config config = configMapper.getByKey(key);
        return config.getValue();
    }

    @Override
    public Integer getInt(String key) {
        Config config = configMapper.getByKey(key);
        return Integer.valueOf(config.getValue());
    }

    @Override
    public Long getLong(String key) {
        Config config = configMapper.getByKey(key);
        return Long.valueOf(config.getValue());
    }
}
