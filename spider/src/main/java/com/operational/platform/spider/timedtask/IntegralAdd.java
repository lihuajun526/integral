package com.operational.platform.spider.timedtask;

import com.operational.platform.dbservice.model.IntegralRecord;
import com.operational.platform.dbservice.model.User;
import com.operational.platform.dbservice.service.IntegralRecordService;
import com.operational.platform.dbservice.service.ScoreService;
import com.operational.platform.dbservice.service.UserService;
import com.operational.platform.spider.bean.SpringContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by lihuajun on 16-7-19.
 */
public class IntegralAdd {

    private static final Logger LOGGER = LoggerFactory.getLogger(IntegralAdd.class);

    public static void execute() {

        UserService userService = (UserService) SpringContext.getContext().getBean("userService");

        List<User> userList = userService.listIntegralNotEnough();
        for (User user : userList) {
            //todo 上线时打开
            /*if(StringUtils.isEmpty(user.getOpenid()))//未关注公众号
                continue;*/
            IntegralRecord integralRecord = new IntegralRecord();
            integralRecord.setDescription("定时奖励积分");
            integralRecord.setUserid(user.getId());
            integralRecord.setIntegral(100);
            integralRecord.setType(15);
            integralRecord.setTag("奖励");
            userService.encourageIntegral(user, integralRecord);
        }

    }

}
