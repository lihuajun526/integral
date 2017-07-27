package com.operational.platform.dbservice.service.impl;

import com.operational.platform.dbservice.dao.AppVersionMapper;
import com.operational.platform.dbservice.dao.RegularMapper;
import com.operational.platform.dbservice.dao.SuggestionMapper;
import com.operational.platform.dbservice.model.*;
import com.operational.platform.dbservice.service.BaseInfoService;
import com.operational.platform.dbservice.service.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lihuajun on 2016/8/15.
 */
@Service("baseInfoService")
public class BaseInfoServiceImpl implements BaseInfoService {

    @Autowired
    private AppVersionMapper appVersionMapper;
    @Autowired
    private RegularMapper regularMapper;

    @Override
    public AppVersion getLatestVersion(String appType) {

        AppVersionExample example = new AppVersionExample();
        AppVersionExample.Criteria criteria = example.createCriteria();
        criteria.andAppTypeEqualTo(appType.toLowerCase());
        List<AppVersion> list = appVersionMapper.selectByExample(example);
        if (list.size() == 0)
            return null;
        else
            return list.get(0);
    }

    @Override
    public List<Regular> listAll() {
        return regularMapper.selectByExample(new RegularExample());
    }


}
