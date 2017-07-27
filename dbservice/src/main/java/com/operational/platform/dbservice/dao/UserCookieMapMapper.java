package com.operational.platform.dbservice.dao;

import com.operational.platform.dbservice.model.UserCookieMap;
import com.operational.platform.dbservice.model.UserCookieMapExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserCookieMapMapper {
    int countByExample(UserCookieMapExample example);

    int deleteByExample(UserCookieMapExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserCookieMap record);

    int insertSelective(UserCookieMap record);

    List<UserCookieMap> selectByExample(UserCookieMapExample example);

    UserCookieMap selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserCookieMap record, @Param("example") UserCookieMapExample example);

    int updateByExample(@Param("record") UserCookieMap record, @Param("example") UserCookieMapExample example);

    int updateByPrimaryKeySelective(UserCookieMap record);

    int updateByPrimaryKey(UserCookieMap record);
}