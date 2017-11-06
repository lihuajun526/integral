package com.operational.platform.dbservice.service.impl;

import com.operational.platform.dbservice.dao.AttackerAttrMapper;
import com.operational.platform.dbservice.model.AttackerAttr;
import com.operational.platform.dbservice.model.AttackerAttrExample;
import com.operational.platform.dbservice.service.AttackerAttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lihuajun on 2017/11/3.
 */
@Service
public class AttackerAttrServiceImpl implements AttackerAttrService {

    @Autowired
    private AttackerAttrMapper attackerAttrMapper;


    @Override
    public AttackerAttr getByNameAndBelong(String name, String belong) {
        AttackerAttrExample example = new AttackerAttrExample();
        AttackerAttrExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        criteria.andBelongEqualTo(belong);

        return attackerAttrMapper.selectByExample(example).get(0);
    }

    @Override
    public List<AttackerAttr> listByBelong(String belong) {
        AttackerAttrExample example = new AttackerAttrExample();
        AttackerAttrExample.Criteria criteria = example.createCriteria();
        criteria.andBelongEqualTo(belong);

        return attackerAttrMapper.selectByExampleWithBLOBs(example);
    }
}
