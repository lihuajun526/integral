package com.operational.platform.dbservice.service;

import com.operational.platform.dbservice.model.AttackerAttr;

import java.util.List;

/**
 * Created by lihuajun on 2017/11/3.
 */
public interface AttackerAttrService {

    AttackerAttr getByNameAndBelong(String name,String belong);

    List<AttackerAttr> listByBelong(String belong);

}
