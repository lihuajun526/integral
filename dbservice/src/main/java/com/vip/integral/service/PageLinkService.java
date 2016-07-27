package com.vip.integral.service;

import com.vip.integral.model.AttackPage;

import java.util.List;

/**
 * Created by lihuajun on 16-7-21.
 */
public interface PageLinkService {

    List<AttackPage> listByBelong(String belong);

}
