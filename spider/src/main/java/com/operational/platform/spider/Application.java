package com.operational.platform.spider;

import com.operational.platform.spider.bean.SpringContext;

/**
 * Created by lihuajun on 2017/8/1.
 */
public class Application {

    public static void main(String[] args) {

        //初始化
        SpringContext.init("classpath:spring/spring.xml");

    }

}
