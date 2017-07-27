package com.operational.platform.dbservice.service;

import com.operational.platform.dbservice.model.AppVersion;
import com.operational.platform.dbservice.model.Regular;
import com.operational.platform.dbservice.model.Suggestion;

import java.util.List;

/**
 * Created by lihuajun on 2016/8/15.
 */
public interface BaseInfoService {

    AppVersion getLatestVersion(String appType);

    List<Regular> listAll();

}
