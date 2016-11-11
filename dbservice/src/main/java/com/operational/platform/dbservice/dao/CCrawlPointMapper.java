package com.operational.platform.dbservice.dao;


import com.operational.platform.dbservice.model.CCrawlPoint;

public interface CCrawlPointMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CCrawlPoint record);

    int insertSelective(CCrawlPoint record);

    CCrawlPoint selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CCrawlPoint record);

    int updateByPrimaryKey(CCrawlPoint record);
}