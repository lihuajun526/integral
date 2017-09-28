package com.operational.platform.analyze;


import com.operational.platform.analyze.bean.SpringContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

    private final static Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {

        SpringContext.init("classpath:spring/spring.xml");

    }

}