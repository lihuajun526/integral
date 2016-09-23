package com.vip.dbservice.dao;

import com.vip.dbservice.model.CrawlPoint;

import java.util.List;

public interface CrawlPointMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CrawlPoint record);

    int insertSelective(CrawlPoint record);

    CrawlPoint selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CrawlPoint record);

    int updateByPrimaryKey(CrawlPoint record);

    List<CrawlPoint> selectByCondition(CrawlPoint crawlPoint);

    CrawlPoint getByNode(Integer nodeid);
}