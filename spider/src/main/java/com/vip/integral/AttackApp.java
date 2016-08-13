package com.vip.integral;

import com.vip.integral.attack.tengxun.task.CommentAttackTask;
import com.vip.integral.bean.SpringContext;

/**
 * Created by lihuajun on 16-7-27.
 */
public class AttackApp {

    public static void main(String[] args) {
        //初始化
        SpringContext.init("classpath:spring/spring.xml");
        new Thread(new CommentAttackTask()).start();
    }

}
