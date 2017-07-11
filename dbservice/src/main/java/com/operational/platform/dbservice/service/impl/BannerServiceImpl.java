package com.operational.platform.dbservice.service.impl;

import com.operational.platform.dbservice.dao.BannerMapper;
import com.operational.platform.dbservice.model.Banner;
import com.operational.platform.dbservice.model.BannerExample;
import com.operational.platform.dbservice.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lihuajun on 2017/7/11.
 */
@Service("bannerService")
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public List<Banner> listByForum(Integer forum) {

        BannerExample example = new BannerExample();
        BannerExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(1);
        criteria.andForumEqualTo(forum);
        example.setOrderByClause("order_no");

        return bannerMapper.selectByExample(example);
    }
}
