package com.operational.platform.dbservice.service.impl;

import com.operational.platform.dbservice.dao.PlayRecordMapper;
import com.operational.platform.dbservice.model.PlayRecord;
import com.operational.platform.dbservice.model.PlayRecordExample;
import com.operational.platform.dbservice.model.User;
import com.operational.platform.dbservice.service.PlayRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lihuajun on 2017/7/19.
 */
@Service("playRecordService")
public class PlayRecordServiceImpl implements PlayRecordService {

    @Autowired
    private PlayRecordMapper playRecordMapper;

    @Override
    public void save(PlayRecord playRecord) {
        playRecordMapper.insert(playRecord);
    }

    @Override
    public List<PlayRecord> listLatestByUser(User user) {

        PlayRecordExample example = new PlayRecordExample();
        PlayRecordExample.Criteria criteria = example.createCriteria();
        criteria.andUseridEqualTo(user.getId());
        example.setOrderByClause("create_time desc");
        List<PlayRecord> list = playRecordMapper.selectByExample(example);
        if (list.size() > 10)
            return list.subList(0, 10);
        return list;
    }
}
