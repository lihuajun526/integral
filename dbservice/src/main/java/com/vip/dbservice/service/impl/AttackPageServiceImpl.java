package com.vip.dbservice.service.impl;

import com.vip.dbservice.dao.AttackPageMapper;
import com.vip.dbservice.service.AttackPageService;
import com.vip.dbservice.model.AttackPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lihuajun on 16-7-27.
 */
@Service("attackPageService")
public class AttackPageServiceImpl implements AttackPageService {

    @Autowired
    private AttackPageMapper attackPageMapper;

    @Override public int save(AttackPage attackPage) {
        return attackPageMapper.insert(attackPage);
    }

    @Override public List<AttackPage> listByBelong(String belong) {
        return attackPageMapper.listByBelong(belong);
    }

    @Override
    public AttackPage selectByPrimaryKey(Integer id) {
        return attackPageMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<AttackPage> listByCondition(AttackPage attackPage) {
        return attackPageMapper.listByCondition(attackPage);
    }

    @Override
    public int addCount(AttackPage attackPage) {
        return attackPageMapper.updateByPrimaryKeySelective(attackPage);
    }
}
