package com.vip.integral.service;

import com.vip.integral.model.AttackParam;

import java.util.List;

/**
 * Created by lihuajun on 16-7-21.
 */
public interface AttackParamService {

    List<AttackParam> listByBelong(String belong);

}
