package com.operational.plaform.taskbreak.breaker;

import com.operational.plaform.taskbreak.bean.BreakJob;
import com.operational.plaform.taskbreak.bean.BreakTask;

import java.util.List;

/**
 * Created by lihuajun on 2017/9/27.
 */
public abstract class ABreaker {

    List<BreakJob> exe(BreakTask breakTask) {
        return null;
    }

}
