package com.operational.platform.taskbreak;


import com.operational.platform.taskbreak.bean.SpringContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

    private final static Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {

        SpringContext.init("classpath:spring/spring.xml");

        TzrTask.exe();
    }

}