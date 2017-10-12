package com.operational.platform.taskbreak.breaker;

import com.operational.platform.common.bean.MQCrawlJob;
import com.operational.platform.common.exception.CommonException;
import com.operational.platform.taskbreak.bean.BreakTask;
import com.operational.platform.taskbreak.bean.ListPage;
import com.operational.platform.taskbreak.service.MqService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created by lihuajun on 2017/9/27.
 */
public abstract class ABreaker {

    protected static final Logger LOGGER = LoggerFactory.getLogger(ABreaker.class);
    @Autowired
    private MqService mqService;

    public void saveToMq(ListPage listPage, BreakTask breakTask) {
        MQCrawlJob crawlJob = new MQCrawlJob();
        crawlJob.setListPageEmpty(false);
        crawlJob.setListPage(listPage.getContent());
        crawlJob.setPageIndex(listPage.getPageIndex());
        crawlJob.setAttr(listPage.getAttr());
        crawlJob.setPointid(breakTask.getPointid());
        crawlJob.setTaskid(breakTask.getTaskid());
        mqService.saveToMq(crawlJob);
    }

    public abstract void exe(BreakTask breakTask, Map<String, String> attr) throws CommonException;

}
