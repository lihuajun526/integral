package com.vip.spider.attack.qzone.task;

import com.alibaba.fastjson.JSONObject;
import com.vip.spider.attack.qzone.bean.AttackAttr;
import com.vip.spider.attack.qzone.bean.QQUserInfo;
import com.vip.spider.constant.Belong;
import com.vip.spider.attack.qzone.QZoneCommenter;
import com.vip.spider.bean.SpringContext;
import com.vip.dbservice.model.AttackPage;
import com.vip.dbservice.model.AttackParam;
import com.vip.dbservice.service.AttackPageService;
import com.vip.dbservice.service.AttackParamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihuajun on 2016/8/5.
 */
public class AttackTask implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(AttackTask.class);

    @Override
    public void run() {
        {
            // 获得所有的评论水军
            List<QZoneCommenter> commenters = allCommenter();

            // 获得评论详细页
            List<AttackPage> attackPageList = listAttackPage();

            for (int i = 0; i < attackPageList.size(); i++) {
                AttackPage attackPage = attackPageList.get(i);
                try {
                    AttackAttr attackAttr = JSONObject.parseObject(attackPage.getAttr(), AttackAttr.class);
                    QQUserInfo qqUserInfo = attackAttr.getUserInfo();
                    QZoneCommenter fixCommenter = getCommenterBySex(commenters, qqUserInfo.getSex());
                    if (fixCommenter == null) {
                        LOGGER.warn("{}的性别为{}，找不到合适的攻击者", qqUserInfo.getUin(), qqUserInfo.getSex());
                        continue;
                    }
                    //评论
                    fixCommenter.setAttackPage(attackPage);
                    fixCommenter.setAttackAttr(attackAttr);
                    fixCommenter.comment();
                } catch (Exception e) {
                    LOGGER.error("评论失败:", e);
                }
            }

        }
    }

    /**
     * 获得所有评论者
     *
     * @return
     */
    private List<QZoneCommenter> allCommenter() {

        List<QZoneCommenter> commenters = new ArrayList<>();

        AttackParamService attackParamService = (AttackParamService) SpringContext.getContext().getBean("attackParamService");
        List<AttackParam> list = attackParamService.listByBelong(Belong.QZONE.value());

        for (AttackParam attackParam : list) {
            QZoneCommenter qZoneCommenter = new QZoneCommenter();
            qZoneCommenter.setAttackParam(attackParam);
            qZoneCommenter.setSex(JSONObject.parseObject(attackParam.getAttr()).getInteger("sex"));
            commenters.add(qZoneCommenter);
        }

        return commenters;
    }

    /**
     * 选择异性评论者
     *
     * @param commenters
     * @param sex
     * @return
     */
    private QZoneCommenter getCommenterBySex(List<QZoneCommenter> commenters, Integer sex) {
        for (QZoneCommenter qZoneCommenter : commenters) {
            if (qZoneCommenter.getSex() != sex) {
                return qZoneCommenter;
            }
        }
        return null;
    }

    /**
     * 获得评论目标
     *
     * @return
     */
    private List<AttackPage> listAttackPage() {

        AttackPageService attackPageService = (AttackPageService) SpringContext.getContext().getBean("attackPageService");
        List<AttackPage> attackPageList = attackPageService.listByBelong(Belong.QZONE.value());
        List<AttackPage> list = new ArrayList<>();
        for (AttackPage attackPage : attackPageList) {
            AttackAttr attackAttr = JSONObject.parseObject(attackPage.getAttr(), AttackAttr.class);
            if (attackAttr.getStatus() == 1)
                list.add(attackPage);
        }

        return list;
    }
}
