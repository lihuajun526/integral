package com.operational.platform.dbservice.service;

import com.operational.platform.dbservice.model.AttackParam;

import java.util.List;

/**
 * Created by lihuajun on 16-7-21.
 */
public interface AttackParamService {

    List<AttackParam> listByBelong(String belong);

    int save(AttackParam attackParam);

    int update(AttackParam attackParam);

    AttackParam getByNode(Integer nodeid);

}
