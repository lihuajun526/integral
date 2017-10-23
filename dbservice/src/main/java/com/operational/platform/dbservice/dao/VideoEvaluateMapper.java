package com.operational.platform.dbservice.dao;

import com.operational.platform.dbservice.model.VideoEvaluate;
import com.operational.platform.dbservice.model.VideoEvaluateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VideoEvaluateMapper {
    int countByExample(VideoEvaluateExample example);

    int deleteByExample(VideoEvaluateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(VideoEvaluate record);

    int insertSelective(VideoEvaluate record);

    List<VideoEvaluate> selectByExample(VideoEvaluateExample example);

    VideoEvaluate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VideoEvaluate record, @Param("example") VideoEvaluateExample example);

    int updateByExample(@Param("record") VideoEvaluate record, @Param("example") VideoEvaluateExample example);

    int updateByPrimaryKeySelective(VideoEvaluate record);

    int updateByPrimaryKey(VideoEvaluate record);
}