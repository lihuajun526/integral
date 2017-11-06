package com.operational.platform.dbservice.service;

import com.operational.platform.dbservice.model.AttackTask;

import java.util.List;

/**
 * Created by lihuajun on 2017/11/3.
 */
public interface AttackTaskService {

    AttackTask getOneByBelongAndStatus(String belong, List<Integer> status);

    List<AttackTask> listByBelongAndStatus(String belong, List<Integer> status, Integer limit);

    void save(AttackTask attackTask);

}
