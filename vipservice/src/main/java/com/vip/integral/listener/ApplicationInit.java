package com.vip.integral.listener;

import com.vip.integral.task.InitWechatConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by lihuajun on 2016/9/12.
 */
public class ApplicationInit implements ServletContextListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationInit.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        LOGGER.info("##############################系统初始化开始##############################");

        InitWechatConstant.run();

        LOGGER.info("##############################系统初始化结束##############################");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
