package com.vip.spider;

import com.vip.dbservice.service.AttackPageService;
import com.vip.spider.attack.zhihu.task.AttackTask;
import com.vip.spider.attack.zhihu.task.FollowAttackTask;
import com.vip.spider.bean.SpringContext;

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
