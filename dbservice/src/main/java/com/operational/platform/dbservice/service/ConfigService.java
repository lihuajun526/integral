package com.operational.platform.dbservice.service;

/**
 * Created by lihuajun on 2016/8/15.
 */
public interface ConfigService {

    String getString(String skey);

    Integer getInt(String skey);

    Long getLong(String skey);

}
