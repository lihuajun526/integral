package com.operational.platform.spider.timedtask;

import com.operational.platform.common.util.Config;
import com.operational.platform.dbservice.model.UserCookieMap;
import com.operational.platform.dbservice.service.UserCookieMapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class CookieClearner {

    private static final Logger LOGGER = LoggerFactory.getLogger(CookieClearner.class);

    @Autowired
    private UserCookieMapService userCookieMapService;

    public void execute() {

        LOGGER.info("======================>开始清理cookie");

        int interval = Config.getInt("app.update.cookie.interval");
        int sum = 0;

        List<UserCookieMap> list = userCookieMapService.listAll();
        for (UserCookieMap userCookieMap : list) {
            if ((System.currentTimeMillis() - userCookieMap.getUpdateTime().getTime()) < (1000 * 60 * interval))
                continue;
            userCookieMapService.clearCookie(userCookieMap);
            sum++;
        }

        LOGGER.info("======================>共清理了{}个cookie", sum);
    }

}
