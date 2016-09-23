package com.vip.dbservice.service.impl;

import com.vip.dbservice.dao.AttackParamMapper;
import com.vip.dbservice.model.AttackParam;
import com.vip.dbservice.service.AttackParamService;
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

    @Override public List<AttackParam> listByBelong(String belong) {
        return attackParamMapper.listByBelong(belong);
    }

    @Override public int save(AttackParam attackParam) {
        return attackParamMapper.insert(attackParam);
    }

    @Override public int update(AttackParam attackParam) {
        return attackParamMapper.updateByPrimaryKeySelective(attackParam);
    }

    @Override public AttackParam getByNode(Integer nodeid) {
        return attackParamMapper.getByNode(nodeid);
    }
}
