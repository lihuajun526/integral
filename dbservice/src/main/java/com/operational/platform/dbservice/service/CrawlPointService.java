package com.operational.platform.dbservice.service;

import com.operational.platform.dbservice.model.CrawlPoint;
import com.operational.platform.dbservice.model.CrawlPointExample;

import java.util.List;

/**
 * Created by lihuajun on 16-7-19.
 */
public interface CrawlPointService {

    //获得所有采集点
    List<CrawlPoint> list(CrawlPoint query);

    //保存采集点
    int save(CrawlPoint crawlPoint);

    //更新采集点
    int update(CrawlPoint crawlPoint);

    //查询
    CrawlPoint getByNode(Integer nodeid);

    CrawlPoint selectByPrimaryKey(Integer id);

    List<CrawlPoint> listByExample(CrawlPointExample example);

}
