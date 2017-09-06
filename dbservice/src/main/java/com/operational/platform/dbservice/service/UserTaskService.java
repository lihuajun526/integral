package com.operational.platform.dbservice.service;

import com.operational.platform.dbservice.model.UserTask;

import java.util.List;

/**
 * Created by lihuajun on 16-7-6.
 */
public interface UserTaskService {

    List<UserTask> listByUserAndType(Integer userid, Integer type);

    void save(UserTask userTask);

}
