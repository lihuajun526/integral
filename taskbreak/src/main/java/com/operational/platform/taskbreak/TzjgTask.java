package com.operational.platform.taskbreak;

import com.alibaba.fastjson.JSON;
import com.operational.platform.common.bean.MQCrawlJob;
import com.operational.platform.common.exception.Txt2StringException;
import com.operational.platform.common.util.TxtUtil;
import com.operational.platform.taskbreak.bean.BreakTask;
import com.operational.platform.taskbreak.bean.ListPage;
import com.operational.platform.taskbreak.bean.SpringContext;
import com.operational.platform.taskbreak.breaker.ABreaker;
import com.operational.platform.taskbreak.service.MqService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihuajun on 2017/9/27.
 */
public class TzjgTask {

    public static void exe() {

        BreakTask breakTask = new BreakTask();
        breakTask.setTaskid(String.valueOf(System.currentTimeMillis()));
        breakTask.setPointid(40);
        breakTask.setStatus(1);

        ABreaker aBreaker = (ABreaker) SpringContext.getContext().getBean("tzjg");
        MqService mqService = (MqService) SpringContext.getContext().getBean("mqService");
        List<MQCrawlJob> jobs = aBreaker.exe(breakTask);

        for (MQCrawlJob job : jobs) {
            mqService.saveToMq(job);
        }

    }

    /*public static void main(String[] args) throws Txt2StringException {

        SpringContext.init("classpath:spring/spring.xml");

        MqService mqService = (MqService) SpringContext.getContext().getBean("mqService");

        List<ListPage> listPages = JSON.parseArray(TxtUtil.txt2String(new File("C:\\Users\\lihuajun\\Desktop\\smt\\10000+.txt")), ListPage.class);

        for (ListPage listPage : listPages) {
            MQCrawlJob crawlJob = new MQCrawlJob();
            crawlJob.setListPageEmpty(false);
            crawlJob.setListPage(listPage.getContent());
            crawlJob.setPageIndex(listPage.getPageIndex());
            crawlJob.setPointid(40);
            crawlJob.setAttr(listPage.getAttr());
            crawlJob.setTaskid(String.valueOf(System.currentTimeMillis()));

            mqService.saveToMq(crawlJob);
        }
    }*/

}
