package com.operational.platform.taskbreak;

import com.operational.platform.common.exception.CommonException;
import com.operational.platform.taskbreak.bean.BreakTask;
import com.operational.platform.taskbreak.bean.SpringContext;
import com.operational.platform.taskbreak.breaker.ABreaker;

import java.util.Map;

/**
 * Created by lihuajun on 2017/9/27.
 */
public class PcTzrTask {

    public static void exe(Map<String,String> attr) throws CommonException {

        BreakTask breakTask = new BreakTask();
        breakTask.setTaskid(String.valueOf(System.currentTimeMillis()));
        breakTask.setPointid(42);
        breakTask.setStatus(1);

        ABreaker aBreaker = (ABreaker) SpringContext.getContext().getBean("pcTzr");
        aBreaker.exe(breakTask,attr);
    }

}
