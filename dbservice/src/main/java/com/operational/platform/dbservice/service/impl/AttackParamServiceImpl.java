package com.operational.platform.dbservice.service.impl;

import com.operational.platform.dbservice.model.AttackParam;
import com.operational.platform.dbservice.model.AttackParamExample;
import com.operational.platform.dbservice.model.AttackParamWithBLOBs;
import com.operational.platform.dbservice.service.AttackParamService;
import com.operational.platform.dbservice.dao.AttackParamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lihuajun on 16-7-30.
 */
@Service("attackParamService")
public class AttackParamServiceImpl implements AttackParamService {

    @Autowired
    private AttackParamMapper attackParamMapper;

    @Override
    public List<AttackParam> listByBelong(String belong) {
        return attackParamMapper.listByBelong(belong);
    }

    @Override
    public int save(AttackParamWithBLOBs attackParam) {
        return attackParamMapper.insert(attackParam);
    }

    @Override
    public int update(AttackParamWithBLOBs attackParam) {
        return attackParamMapper.updateByPrimaryKeySelective(attackParam);
    }

    @Override
    public AttackParam getByNode(Integer nodeid) {
        return attackParamMapper.getByNode(nodeid);
    }

    @Override
    public List<AttackParamWithBLOBs> listByBelongAndAttackType(String belong, String attackType) {

        AttackParamExample example = new AttackParamExample();
        AttackParamExample.Criteria criteria = example.createCriteria();
        criteria.andBelongEqualTo(belong);
        criteria.andActionTypeEqualTo(attackType);

        return attackParamMapper.selectByExampleWithBLOBs(example);
    }
}
