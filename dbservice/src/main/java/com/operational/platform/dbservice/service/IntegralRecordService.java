package com.operational.platform.dbservice.service;


import com.operational.platform.dbservice.model.IntegralRecord;

import java.util.List;

/**
 * Created by lihuajun on 16-7-6.
 */
public interface IntegralRecordService {

    List<IntegralRecord> selectBySelective(IntegralRecord integralRecord);

}
