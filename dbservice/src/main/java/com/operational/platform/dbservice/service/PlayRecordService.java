package com.operational.platform.dbservice.service;

import com.operational.platform.dbservice.model.PlayRecord;
import com.operational.platform.dbservice.model.User;

import java.util.List;

/**
 * Created by lihuajun on 16-7-6.
 */
public interface PlayRecordService {


    void save(PlayRecord playRecord);

    List<PlayRecord> listLatestByUser(User user);

}
