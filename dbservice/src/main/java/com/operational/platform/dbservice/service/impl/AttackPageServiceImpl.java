package com.operational.platform.dbservice.service.impl;

import com.operational.platform.dbservice.dao.AttackPageMapper;
import com.operational.platform.dbservice.model.AttackPage;
import com.operational.platform.dbservice.model.AttackPageExample;
import com.operational.platform.dbservice.service.AttackPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lihuajun on 16-7-27.
 */
@Service("attackPageService")
public class AttackPageServiceImpl implements AttackPageService {

    @Autowired
    private AttackPageMapper attackPageMapper;

    @Override
    public int save(AttackPage attackPage) {
        if (attackPage.getId() == null)
            return attackPageMapper.insert(attackPage);
        else
            return attackPageMapper.updateByPrimaryKeySelective(attackPage);
    }

    @Override
    public List<AttackPage> listByBelong(String belong) {
        return attackPageMapper.listByBelong(belong);
    }

    @Override
    public List<AttackPage> listByBelongs(List<String> belongs) {

        if (belongs == null || belongs.size() == 0)
            return null;
        AttackPageExample example = new AttackPageExample();
        AttackPageExample.Criteria criteria = example.createCriteria();
        criteria.andBelongIn(belongs);
        return attackPageMapper.selectByExampleWithBLOBs(example);
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

    @Override
    public void del(Integer id) {
        attackPageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void recoverFlag(List<Integer> pointids) {
        if (pointids == null || pointids.size() == 0)
            return;

        AttackPageExample example = new AttackPageExample();
        AttackPageExample.Criteria criteria = example.createCriteria();
        criteria.andPointidIn(pointids);

        AttackPage attackPage = new AttackPage();
        attackPage.setFlag(0);

        attackPageMapper.updateByExampleSelective(attackPage, example);
    }

    @Override
    public List<AttackPage> listByPoints(List<Integer> pointids) {
        AttackPageExample example = new AttackPageExample();
        AttackPageExample.Criteria criteria = example.createCriteria();
        criteria.andPointidIn(pointids);

        example.setOrderByClause("create_time desc");

        return attackPageMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public List<AttackPage> listByPointsAndLimit(List<Integer> pointids, Integer limit) {

        Map<String,Object> map = new HashMap<>();
        map.put("pointids",pointids);
        map.put("limit",limit);

        return attackPageMapper.listByPointsAndLimit(map);
    }

    @Override
    public List<AttackPage> listByPointAndLink(AttackPage attackPage) {
        AttackPageExample example = new AttackPageExample();
        AttackPageExample.Criteria criteria = example.createCriteria();
        criteria.andPointidEqualTo(attackPage.getPointid());
        criteria.andLinkEqualTo(attackPage.getLink());

        return attackPageMapper.selectByExample(example);
    }
}
