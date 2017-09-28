package com.operational.platform.dbservice.service.impl;

import com.operational.platform.dbservice.dao.CrawlJobMapper;
import com.operational.platform.dbservice.model.CrawlJob;
import com.operational.platform.dbservice.model.CrawlJobExample;
import com.operational.platform.dbservice.service.CrawlJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lihuajun on 2016/8/15.
 */
@Service("crawlJobService")
public class CrawlJobServiceImpl implements CrawlJobService {

    @Autowired
    private CrawlJobMapper crawlJobMapper;


    @Override
    public void save(CrawlJob crawlJob) {
        crawlJobMapper.insert(crawlJob);
    }

    @Override
    public List<CrawlJob> listAll() {
        return crawlJobMapper.selectByExampleWithBLOBs(new CrawlJobExample());
    }
}
