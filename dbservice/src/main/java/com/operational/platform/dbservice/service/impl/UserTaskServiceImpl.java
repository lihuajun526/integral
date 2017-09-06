package com.operational.platform.dbservice.service.impl;

import com.operational.platform.dbservice.dao.UserTaskMapper;
import com.operational.platform.dbservice.model.UserTask;
import com.operational.platform.dbservice.model.UserTaskExample;
import com.operational.platform.dbservice.service.UserTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lihuajun on 2016/8/15.
 */
@Service("userTaskService")
public class UserTaskServiceImpl implements UserTaskService {

    @Autowired
    private UserTaskMapper userTaskMapper;

    @Override
    public List<UserTask> listByUserAndType(Integer userid, Integer type) {

        UserTaskExample example = new UserTaskExample();
        UserTaskExample.Criteria criteria = example.createCriteria();
        criteria.andUseridEqualTo(userid);
        criteria.andTypeEqualTo(type);

        return userTaskMapper.selectByExample(example);
    }

    @Override
    public void save(UserTask userTask) {
        userTaskMapper.insert(userTask);
    }
}
