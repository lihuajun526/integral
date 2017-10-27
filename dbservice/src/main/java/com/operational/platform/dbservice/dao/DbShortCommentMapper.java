package com.operational.platform.dbservice.dao;

import com.operational.platform.dbservice.model.DbShortComment;
import com.operational.platform.dbservice.model.DbShortCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DbShortCommentMapper {
    int countByExample(DbShortCommentExample example);

    int deleteByExample(DbShortCommentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DbShortComment record);

    int insertSelective(DbShortComment record);

    List<DbShortComment> selectByExample(DbShortCommentExample example);

    DbShortComment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DbShortComment record, @Param("example") DbShortCommentExample example);

    int updateByExample(@Param("record") DbShortComment record, @Param("example") DbShortCommentExample example);

    int updateByPrimaryKeySelective(DbShortComment record);

    int updateByPrimaryKey(DbShortComment record);
}