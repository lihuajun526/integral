package com.operational.platform.dbservice.service;

import com.operational.platform.dbservice.model.AttackPage;

import java.util.List;

/**
 * Created by lihuajun on 16-7-27.
 */
public interface AttackPageService {

    int save(AttackPage attackPage);

    List<AttackPage> listByBelong(String belong);

    AttackPage selectByPrimaryKey(Integer id);

    List<AttackPage> listByCondition(AttackPage attackPage);

    int addCount(AttackPage attackPage);

}
