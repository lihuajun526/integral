package com.vip.dbservice.service.impl;


import com.vip.dbservice.dao.BreakRuleMapper;
import com.vip.dbservice.model.BreakRule;
import com.vip.dbservice.service.BreakRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author: Zhou Xuanang
 * @Date: 13:40 2016/10/31.
 */
@Service("breakRuleService")
public class BreakRuleServiceImpl implements BreakRuleService {
    @Autowired
    private BreakRuleMapper breakRuleMapper;

    @Override
    public List<BreakRule> getByCrawlId(Integer id) {
        /*BreakRuleExample breakRuleExample = new BreakRuleExample();
        BreakRuleExample.Criteria criteria = breakRuleExample.createCriteria();
        criteria.andCrawlPointIdEqualTo(id);
        return breakRuleMapper.selectByExample(breakRuleExample);*/
        return null;
    }

    @Override
    public BreakRule getById(Integer id) {
        return breakRuleMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delete(Integer id) {
        breakRuleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void save(BreakRule breakRule) {
        if (breakRule.getId() == 0) {
            List<BreakRule> breakRuleList = breakRuleMapper.selectByCrawlOrderByLevel(breakRule.getCrawlPointId());
            if (breakRuleList != null && breakRuleList.size() > 0) {
                breakRule.setLevel(breakRuleList.get(breakRuleList.size() - 1).getLevel() + 1);
            } else {
                breakRule.setLevel(1);
            }
            breakRuleMapper.insert(breakRule);
        } else {
            breakRule.setUpdateTime(new Date());
            breakRuleMapper.updateByPrimaryKeySelective(breakRule);
        }
    }
}
