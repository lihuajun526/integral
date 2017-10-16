package com.operational.platform.dbservice.service.impl;

import com.operational.platform.common.bean.WechatMsg;
import com.operational.platform.dbservice.service.ConfigService;
import com.operational.platform.dbservice.service.WechatService;
import com.operational.platform.dbservice.model.User;
import com.operational.platform.dbservice.service.IntegralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by lihuajun on 2016/8/18.
 */
@Service("wechatService")
public class WechatServiceImpl implements WechatService {

    //    @Autowired
//    private UserMapper userMapper;
    @Autowired
    private ConfigService configService;
    @Autowired
    private IntegralService integralService;

    @Override
    public String handle(WechatMsg wechatMsg) {

        String result = "";
        User user = null;


        if (wechatMsg.getMsgType().equalsIgnoreCase("text")) {

        } else if (true) {//关注
            return subscribe(user, wechatMsg);
        } else if (true) {//取消关注
            return unsubscribe(user, wechatMsg);
        }

        return result;
    }

    /**
     * 关注
     *
     * @param user
     * @param wechatMsg
     * @return
     */
    private String subscribe(User user, WechatMsg wechatMsg) {

        String result = "";

        if (user == null) {//新用户
            user = new User();
            user.setOpenid(wechatMsg.getFromUserName());
            //todo 拉取用户详细信息
            user.setIntegral(configService.getInt("subscribe.integral.encourage"));
            //todo save用户
            //奖励推广者
            String friendid = wechatMsg.getEventKey().split("_")[1];
            if (!StringUtils.isEmpty(friendid)) {
                integralService.encourageFromPopularize(Integer.valueOf(friendid), user.getId(), configService.getInt("popularize.integral.encourage"));
            }
        } else {//老用户
            if (user.getStatus() == 0) {
                user.setStatus(1);
            }
            //todo 更新用户
        }

        return result;
    }

    /**
     * 取消关注
     *
     * @param user
     * @param wechatMsg
     * @return
     */
    private String unsubscribe(User user, WechatMsg wechatMsg) {
        String result = "";
        user.setStatus(0);
        //todo 更新用户
        return result;
    }
}
