package com.operational.platform.dbservice.dao;

import com.operational.platform.dbservice.model.CrawlPoint;
import com.operational.platform.dbservice.model.CrawlPointExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CrawlPointMapper {
    int countByExample(CrawlPointExample example);

    int deleteByExample(CrawlPointExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CrawlPoint record);

    int insertSelective(CrawlPoint record);

    List<CrawlPoint> selectByExample(CrawlPointExample example);

    CrawlPoint selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CrawlPoint record, @Param("example") CrawlPointExample example);

    int updateByExample(@Param("record") CrawlPoint record, @Param("example") CrawlPointExample example);

    int updateByPrimaryKeySelective(CrawlPoint record);

    int updateByPrimaryKey(CrawlPoint record);

    List<CrawlPoint> selectByCondition(CrawlPoint crawlPoint);

    CrawlPoint getByNode(Integer nodeid);
}