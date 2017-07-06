package com.operational.platform.dbservice.service;

import com.operational.platform.dbservice.model.AttackParam;
import com.operational.platform.dbservice.model.AttackParamWithBLOBs;

import java.util.List;

/**
 * Created by lihuajun on 16-7-21.
 */
public interface AttackParamService {

    List<AttackParam> listByBelong(String belong);

    int save(AttackParamWithBLOBs attackParam);

    int update(AttackParamWithBLOBs attackParam);

    AttackParam getByNode(Integer nodeid);

    List<AttackParamWithBLOBs> listByBelongAndAttackType(String belong,String attackType);

}
