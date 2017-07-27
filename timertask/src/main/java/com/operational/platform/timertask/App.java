package com.operational.platform.timertask;

import com.operational.platform.timertask.bean.SpringContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lihuajun on 16-7-27.
 */
public class App {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        //初始化
        SpringContext.init("classpath:spring/spring.xml");

        LOGGER.info("===========================>定时调度器启动成功");

    }

}
