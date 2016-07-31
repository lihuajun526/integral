package com.vip.integral.service.impl;

import com.vip.integral.dao.AttackPageMapper;
import com.vip.integral.model.AttackPage;
import com.vip.integral.service.AttackPageService;
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
}
