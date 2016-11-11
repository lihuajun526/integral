package com.vip.dbservice.service;

import com.vip.dbservice.model.CCrawlPoint;

import java.util.List;

/**
 * @author: Zhou Xuanang
 * @Date: 13:41 2016/11/4.
 */
public interface CCrawlPointService {
    List<CCrawlPoint> getByParentId(Integer parentId);

    void save(CCrawlPoint cCrawlPoint);

    void update(CCrawlPoint cCrawlPoint);
}
