package com.operational.platform.spider;

import com.operational.platform.dbservice.model.AttackPage;
import com.operational.platform.dbservice.service.AttackPageService;
import com.operational.platform.dbservice.service.ScoreService;
import com.operational.platform.dbservice.service.VideoSuggestService;
import com.operational.platform.spider.attack.zhihu.task.FollowAttackTask;
import com.operational.platform.spider.bean.SpringContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihuajun on 16-7-27.
 */
public class ScoreApp {

    public static void main(String[] args) {
        //初始化
        SpringContext.init("classpath:spring/spring.xml");

        List<String> belongs = new ArrayList<>();
        belongs.add("aqy");

        ScoreService scoreService = (ScoreService) SpringContext.getContext().getBean("scoreService");
        scoreService.score(belongs);
    }

}
