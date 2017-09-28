package com.operational.platform.taskbreak.breaker;

import com.operational.platform.common.bean.MQCrawlJob;
import com.operational.platform.taskbreak.bean.BreakTask;
import com.operational.platform.taskbreak.bean.ListPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihuajun on 2017/9/27.
 */
public abstract class ABreaker {

    protected static final Logger LOGGER = LoggerFactory.getLogger(ABreaker.class);

    public List<MQCrawlJob> exe(BreakTask breakTask) {

        List<ListPage> listPages = getListPage();
        if (listPages == null || listPages.size() == 0) {
            LOGGER.info("分解任务[id={},pointid={}]没有分解出job", breakTask.getTaskid(), breakTask.getPointid());
            return null;
        }

        List<MQCrawlJob> list = new ArrayList<>();
        for (ListPage listPage : listPages) {
            MQCrawlJob crawlJob = new MQCrawlJob();
            crawlJob.setIsListPageEmpty(false);
            crawlJob.setListPage(listPage.getContent());
            crawlJob.setPageIndex(listPage.getPageIndex());
            crawlJob.setPointid(breakTask.getPointid());
            crawlJob.setTaskid(breakTask.getTaskid());
            list.add(crawlJob);
        }

        return list;
    }


    protected abstract List<ListPage> getListPage();

}
