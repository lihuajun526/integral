package com.vip.taskbreak;

import com.vip.dbservice.model.CCrawlPoint;
import com.vip.dbservice.service.CCrawlPointService;
import com.vip.taskbreak.component.BreakFactory;
import com.vip.taskbreak.entity.DataSource;
import com.vip.taskbreak.util.SpringContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.util.List;

/**
 * 任务分解处理器
 * 
 * @author: Zhou Xuanang
 * @Date: 14:18 2016/11/1.
 */
public class BreakProcessor implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(BreakProcessor.class);

    ApplicationContext atx = SpringContext.getContext();
    private BreakFactory breakFactory = (BreakFactory) atx.getBean("breakFactory");
    private CCrawlPointService cCrawlPointService = (CCrawlPointService) atx.getBean("cCrawlPointService");

    private String taskId;
    private List<DataSource> dataSourceList;

    public BreakProcessor(List<DataSource> dataSourceList) {
        this.dataSourceList = dataSourceList;
        this.taskId = dataSourceList.get(0).getTaskId();
    }

    @Override
    public void run() {
        LOGGER.info("======>[TaskID:{}]任务开始...", taskId);

        for (DataSource dataSource : dataSourceList) {
            LOGGER.info("[TaskID:{},ParentID:{}],开始分解数据源...", taskId, dataSource.getParentId());

            breakFactory.init(dataSource);

            // 获取列表页(采集点)url集合
            List<String> urlList = breakFactory.process();

            // 获取列表页(采集点)的总页数和总条数
            List<CCrawlPoint> crawlList = breakFactory.requestListPage(urlList);

            for (CCrawlPoint cCrawlPoint : crawlList) {

                cCrawlPoint.setParentId(dataSource.getParentId());
                cCrawlPointService.save(cCrawlPoint);
            }

            LOGGER.info("======>[TaskID:{},ParentID:{}],数据源分解完成,得到{}个子采集点", taskId, dataSource.getParentId(), crawlList.size());
        }

        LOGGER.info("======>[TaskID:{}]任务结束!", taskId);

        // 提交iCrawlGroupList至任务队列

    }
}
