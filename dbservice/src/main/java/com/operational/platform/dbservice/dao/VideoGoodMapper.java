package com.operational.platform.dbservice.dao;

import com.operational.platform.dbservice.model.VideoGood;
import com.operational.platform.dbservice.model.VideoGoodExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VideoGoodMapper {
    int countByExample(VideoGoodExample example);

    int deleteByExample(VideoGoodExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(VideoGood record);

    int insertSelective(VideoGood record);

    List<VideoGood> selectByExample(VideoGoodExample example);

    VideoGood selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VideoGood record, @Param("example") VideoGoodExample example);

    int updateByExample(@Param("record") VideoGood record, @Param("example") VideoGoodExample example);

    int updateByPrimaryKeySelective(VideoGood record);

    int updateByPrimaryKey(VideoGood record);

    List<VideoGood> getLatest();
}