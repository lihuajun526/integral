package com.vip.integral.dao;

import com.vip.integral.model.CrawlPoint;

public interface CrawlPointMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CrawlPoint record);

    int insertSelective(CrawlPoint record);

    CrawlPoint selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CrawlPoint record);

    int updateByPrimaryKey(CrawlPoint record);
}