package com.operational.platform.taskbreak;

import com.operational.platform.taskbreak.bean.BreakTask;
import com.operational.platform.taskbreak.bean.SpringContext;
import com.operational.platform.taskbreak.breaker.ABreaker;

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
        //aBreaker.exe(breakTask);
    }
}
