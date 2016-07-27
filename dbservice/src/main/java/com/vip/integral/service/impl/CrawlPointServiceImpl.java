package com.vip.integral.service.impl;

import com.vip.integral.dao.CrawlPointMapper;
import com.vip.integral.model.CrawlPoint;
import com.vip.integral.service.CrawlPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihuajun on 16-7-27.
 */
@Service("crawlPointService")
public class CrawlPointServiceImpl implements CrawlPointService {

    @Autowired
    private CrawlPointMapper crawlPointMapper;

    @Override public List<CrawlPoint> list(CrawlPoint query) {

        if (query.getId() != null) {
            List<CrawlPoint> list = new ArrayList<>();
            CrawlPoint crawlPoint = crawlPointMapper.selectByPrimaryKey(query.getId());
            if (crawlPoint != null)
                list.add(crawlPoint);
            return list;
        }
        return crawlPointMapper.selectByCondition(query);
    }

    @Override public int save(CrawlPoint crawlPoint) {
        return crawlPointMapper.insert(crawlPoint);
    }

    @Override public int update(CrawlPoint crawlPoint) {
        return crawlPointMapper.updateByPrimaryKeySelective(crawlPoint);
    }

    @Override public CrawlPoint getByNode(Integer nodeid) {
        return crawlPointMapper.getByNode(nodeid);
    }
}
