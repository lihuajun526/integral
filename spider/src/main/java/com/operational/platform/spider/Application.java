package com.operational.platform.spider;

import com.operational.platform.spider.bean.SpringContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lihuajun on 2017/8/1.
 */
public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {

        //初始化
        SpringContext.init("classpath:spring/spring.xml");

        LOGGER.info("======================>应用已启动");

    }

}
