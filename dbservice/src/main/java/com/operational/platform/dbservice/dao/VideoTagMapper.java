package com.operational.platform.dbservice.dao;

import com.operational.platform.dbservice.model.VideoTag;
import com.operational.platform.dbservice.model.VideoTagExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VideoTagMapper {
    int countByExample(VideoTagExample example);

    int deleteByExample(VideoTagExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(VideoTag record);

    int insertSelective(VideoTag record);

    List<VideoTag> selectByExample(VideoTagExample example);

    VideoTag selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VideoTag record, @Param("example") VideoTagExample example);

    int updateByExample(@Param("record") VideoTag record, @Param("example") VideoTagExample example);

    int updateByPrimaryKeySelective(VideoTag record);

    int updateByPrimaryKey(VideoTag record);
}