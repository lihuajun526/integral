package com.operational.platform.dbservice.dao;

import com.operational.platform.dbservice.model.VideoSuggest;
import com.operational.platform.dbservice.model.VideoSuggestExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VideoSuggestMapper {
    int countByExample(VideoSuggestExample example);

    int deleteByExample(VideoSuggestExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(VideoSuggest record);

    int insertSelective(VideoSuggest record);

    List<VideoSuggest> selectByExample(VideoSuggestExample example);

    VideoSuggest selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VideoSuggest record, @Param("example") VideoSuggestExample example);

    int updateByExample(@Param("record") VideoSuggest record, @Param("example") VideoSuggestExample example);

    int updateByPrimaryKeySelective(VideoSuggest record);

    int updateByPrimaryKey(VideoSuggest record);
}