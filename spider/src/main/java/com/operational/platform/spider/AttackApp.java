package com.operational.platform.spider;

import com.operational.platform.spider.attack.zhihu.task.FollowAttackTask;
import com.operational.platform.dbservice.service.AttackPageService;
import com.operational.platform.spider.bean.SpringContext;

/**
 * Created by lihuajun on 16-7-27.
 */
public class AttackApp {

    public static void main(String[] args) {
        //初始化
        SpringContext.init("classpath:spring/spring.xml");

        AttackPageService attackPageService = (AttackPageService) SpringContext.getContext().getBean("attackPageService");
        new Thread(new FollowAttackTask(attackPageService)).start();
        //new Thread(new ReplyAttackTask()).start();


    }

}
