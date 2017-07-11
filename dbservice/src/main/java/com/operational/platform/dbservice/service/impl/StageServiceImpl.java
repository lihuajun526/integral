package com.operational.platform.dbservice.service.impl;

import com.operational.platform.dbservice.dao.StageMapper;
import com.operational.platform.dbservice.model.Stage;
import com.operational.platform.dbservice.model.StageExample;
import com.operational.platform.dbservice.service.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lihuajun on 2017/7/11.
 */
@Service("stageService")
public class StageServiceImpl implements StageService {

    @Autowired
    private StageMapper stageMapper;

    @Override
    public List<Stage> list() {

        StageExample example = new StageExample();
        StageExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(1);
        example.setOrderByClause("order_no");

        return stageMapper.selectByExample(example);
    }
}
