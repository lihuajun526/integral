package com.vip.taskbreak;

import com.vip.taskbreak.rule.RulesLoader;
import com.vip.taskbreak.util.SpringContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

    private final static Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {

        SpringContext.init("classpath:spring/spring.xml");
        // 加载规则
        RulesLoader.load();

    }

}