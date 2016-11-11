package com.operational.platform.spider.attack.zhihu.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.operational.platform.dbservice.model.AttackPage;
import com.operational.platform.dbservice.model.AttackParam;
import com.operational.platform.dbservice.service.AttackPageService;
import com.operational.platform.dbservice.service.AttackParamService;
import com.operational.platform.spider.attack.zhihu.UserFollower;
import com.operational.platform.spider.bean.SpringContext;
import com.operational.platform.spider.constant.Belong;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by lihuajun on 2016/8/5.
 */
public class FollowAttackTask implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(FollowAttackTask.class);

    private AttackPageService attackPageService;

    public FollowAttackTask(AttackPageService attackPageService) {
        this.attackPageService = attackPageService;
    }

    @Override
    public void run() {
        {
            // 获得所有消息发送者
            List<UserFollower> followers = allFollower();

            // 获得详细页
            List<AttackPage> attackPageList = listAttackPage();

            try {
                for (int i = 0; i < attackPageList.size(); i++) {
                    AttackPage attackPage = attackPageList.get(i);
                    JSONObject attr = JSONObject.parseObject(attackPage.getAttr());
                    int sex = 2;
                    String sSex = attr.getString("sex");
                    if (sSex != null && "icon icon-profile-female".equals(sSex)) {
                        sex = 1;
                    }
                    UserFollower fixFollower = getSenderBySex(followers, sex);
                    //发送信息
                    fixFollower.setAttackPage(attackPage);
                    fixFollower.follow();
                }
            } catch (Exception e) {
                LOGGER.error("关注失败:", e);
            }
        }
    }

    /**
     * 获得所有评论者
     *
     * @return
     */
    private List<UserFollower> allFollower() {

        List<UserFollower> followers = new ArrayList<>();

        AttackParamService attackParamService = (AttackParamService) SpringContext.getContext().getBean("attackParamService");
        List<AttackParam> list = attackParamService.listByBelong(Belong.ZHIHU.value());

        for (AttackParam attackParam : list) {
            UserFollower userFollower = new UserFollower(attackPageService);
            userFollower.setAttackParam(attackParam);
            followers.add(userFollower);
        }

        return followers;
    }

    /**
     * 选择异性进行关注
     *
     * @param followers
     * @param sex
     * @return
     */
    private UserFollower getSenderBySex(List<UserFollower> followers, Integer sex) {

        List<UserFollower> tempList = new ArrayList<>();
        for (UserFollower follower : followers) {
            JSONObject attr = JSON.parseObject(follower.getAttackParam().getAttr());
            if (attr.getInteger("sex") == sex)
                tempList.add(follower);
        }

        Random random = new Random();
        int index = random.nextInt(tempList.size());

        return tempList.get(index);
    }

    /**
     * 获得信息发送目标
     *
     * @return
     */
    private List<AttackPage> listAttackPage() {

        List<AttackPage> attackPageList = attackPageService.listByBelong(Belong.ZHIHU.value());
        return attackPageList;
    }



}
