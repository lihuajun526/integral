package com.operational.platform.dbservice.service.impl;

import com.operational.platform.dbservice.dao.LogMapper;
import com.operational.platform.dbservice.model.Log;
import com.operational.platform.dbservice.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lihuajun on 2016/8/15.
 */
@Service("logService")
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    public void save(Log log) {
        logMapper.insert(log);
    }
}
