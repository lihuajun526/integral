package com.vip.dbservice.service.impl;

import com.vip.dbservice.dao.CCrawlPointMapper;
import com.vip.dbservice.model.CCrawlPoint;
import com.vip.dbservice.service.CCrawlPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Zhou Xuanang
 * @Date: 13:43 2016/11/4.
 */
@Service("cCrawlPointService")
public class CCrawlPointServiceImpl implements CCrawlPointService {
    @Autowired
    private CCrawlPointMapper cCrawlPointMapper;

    @Override
    public List<CCrawlPoint> getByParentId(Integer parentId) {
        /*CrawlExample crawlExample = new CrawlExample();
        CrawlExample.Criteria criteria = crawlExample.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        return crawlMapper.selectByExample(crawlExample);*/
        return null;
    }

    @Override
    public void save(CCrawlPoint cCrawlPoint) {
        cCrawlPointMapper.insertSelective(cCrawlPoint);
    }

    @Override
    public void update(CCrawlPoint cCrawlPoint) {
        cCrawlPointMapper.updateByPrimaryKeySelective(cCrawlPoint);
    }
}
