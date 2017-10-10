package com.operational.platform.taskbreak;

import com.operational.platform.common.bean.MQCrawlJob;
import com.operational.platform.taskbreak.bean.BreakTask;
import com.operational.platform.taskbreak.bean.SpringContext;
import com.operational.platform.taskbreak.breaker.ABreaker;
import com.operational.platform.taskbreak.service.MqService;

import java.util.List;

/**
 * Created by lihuajun on 2017/9/27.
 */
public class TzrTask {

    public static void exe() {

        BreakTask breakTask = new BreakTask();
        breakTask.setTaskid(String.valueOf(System.currentTimeMillis()));
        breakTask.setPointid(39);
        breakTask.setStatus(1);

        ABreaker aBreaker = (ABreaker) SpringContext.getContext().getBean("tzr");
        MqService mqService = (MqService) SpringContext.getContext().getBean("mqService");
        List<MQCrawlJob> jobs = aBreaker.exe(breakTask);

        for (MQCrawlJob job : jobs) {
            mqService.saveToMq(job);
        }

    }

}
