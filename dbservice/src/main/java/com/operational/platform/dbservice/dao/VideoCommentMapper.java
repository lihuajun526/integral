package com.operational.platform.dbservice.dao;

import com.operational.platform.dbservice.model.VideoComment;
import com.operational.platform.dbservice.model.VideoCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VideoCommentMapper {
    int countByExample(VideoCommentExample example);

    int deleteByExample(VideoCommentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(VideoComment record);

    int insertSelective(VideoComment record);

    List<VideoComment> selectByExample(VideoCommentExample example);

    VideoComment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VideoComment record, @Param("example") VideoCommentExample example);

    int updateByExample(@Param("record") VideoComment record, @Param("example") VideoCommentExample example);

    int updateByPrimaryKeySelective(VideoComment record);

    int updateByPrimaryKey(VideoComment record);
}