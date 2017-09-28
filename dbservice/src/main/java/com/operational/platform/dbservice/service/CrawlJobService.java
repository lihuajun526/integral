package com.operational.platform.dbservice.service;

import com.operational.platform.dbservice.model.CrawlJob;

import java.util.List;

/**
 * Created by lihuajun on 2017/9/28.
 */
public interface CrawlJobService {

    void save(CrawlJob crawlJob);

    List<CrawlJob> listAll();

}
