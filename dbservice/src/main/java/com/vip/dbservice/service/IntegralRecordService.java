package com.vip.dbservice.service;


import com.vip.dbservice.model.IntegralRecord;

import java.util.List;

/**
 * Created by lihuajun on 16-7-6.
 */
public interface IntegralRecordService {

    List<IntegralRecord> selectBySelective(IntegralRecord integralRecord);

}
