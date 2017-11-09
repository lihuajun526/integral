package com.operational.platform.operate;

import com.operational.platform.operate.task.TimerAddQQUser;
import com.operational.platform.operate.task.TimerCrawlQQUser;
import com.operational.platform.operate.task.TimerSendEmail;
import com.operational.platform.operate.util.SpringContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lihuajun on 2017/11/3.
 */
public class App {

    private final static Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {

        SpringContext.init("classpath:spring/spring.xml");

        /*TimerSendEmail timerSendEmail = (TimerSendEmail) SpringContext.getContext().getBean("timerSendEmail");
        timerSendEmail.execute();

        TimerCrawlQQUser timerCrawlQQUser = (TimerCrawlQQUser) SpringContext.getContext().getBean("timerCrawlQQUser");
        timerCrawlQQUser.execute();*/

    }

}
