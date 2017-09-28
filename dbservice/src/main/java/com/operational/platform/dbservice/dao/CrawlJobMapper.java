package com.operational.platform.dbservice.dao;

import com.operational.platform.dbservice.model.CrawlJob;
import com.operational.platform.dbservice.model.CrawlJobExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CrawlJobMapper {
    int countByExample(CrawlJobExample example);

    int deleteByExample(CrawlJobExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CrawlJob record);

    int insertSelective(CrawlJob record);

    List<CrawlJob> selectByExampleWithBLOBs(CrawlJobExample example);

    List<CrawlJob> selectByExample(CrawlJobExample example);

    CrawlJob selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CrawlJob record, @Param("example") CrawlJobExample example);

    int updateByExampleWithBLOBs(@Param("record") CrawlJob record, @Param("example") CrawlJobExample example);

    int updateByExample(@Param("record") CrawlJob record, @Param("example") CrawlJobExample example);

    int updateByPrimaryKeySelective(CrawlJob record);

    int updateByPrimaryKeyWithBLOBs(CrawlJob record);

    int updateByPrimaryKey(CrawlJob record);
}