package com.vip.dbservice.service.impl;

import com.vip.dbservice.dao.IntegralRecordMapper;
import com.vip.dbservice.model.IntegralRecord;
import com.vip.dbservice.service.IntegralRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lihuajun on 2016/8/15.
 */
@Service("integralRecordService")
public class IntegralRecordServiceImpl implements IntegralRecordService {

    @Autowired
    private IntegralRecordMapper integralRecordMapper;

    @Override
    public List<IntegralRecord> selectBySelective(IntegralRecord integralRecord) {
        return integralRecordMapper.selectBySelective(integralRecord);
    }
}
