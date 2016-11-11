package com.operational.platform.dbservice.service;


import com.operational.platform.dbservice.model.BreakRule;

import java.util.List;

/**
 * @author: Zhou Xuanang
 * @Date: 13:39 2016/10/31.
 */
public interface BreakRuleService {
    List<BreakRule> getByCrawlId(Integer crawlId);

    BreakRule getById(Integer id);

    void delete(Integer id);

    void save(BreakRule breakRule);
}
