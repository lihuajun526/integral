package com.vip.integral.service.impl;

import com.vip.integral.dao.AttackParamMapper;
import com.vip.integral.model.AttackParam;
import com.vip.integral.service.AttackParamService;
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
