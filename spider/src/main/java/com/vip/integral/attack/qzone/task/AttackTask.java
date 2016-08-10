package com.vip.integral.attack.qzone.task;

import com.alibaba.fastjson.JSONObject;
import com.vip.integral.attack.qzone.QZoneCommenter;
import com.vip.integral.attack.qzone.bean.QQUserInfo;
import com.vip.integral.bean.SpringContext;
import com.vip.integral.model.AttackPage;
import com.vip.integral.model.AttackParam;
import com.vip.integral.service.AttackParamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static com.vip.integral.constant.Belong.QZONE;

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
            List<AttackPage> attackPageList = null;

            for (int i = 0; i < attackPageList.size(); i++) {
                AttackPage attackPage = attackPageList.get(i);
                try {
                    QQUserInfo qqUserInfo = JSONObject.parseObject(attackPage.getAttr(), QQUserInfo.class);
                    //todo 确定男女值
                    QZoneCommenter fixCommenter = getCommenterBySex(commenters, qqUserInfo.getSex());
                    if (fixCommenter == null) {
                        LOGGER.warn("{}的性别为{}", qqUserInfo.getUin(), qqUserInfo.getSex());
                        continue;
                    }
                    //评论
                    fixCommenter.comment();
                } catch (Exception e) {
                    e.printStackTrace();
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
        List<AttackParam> list = attackParamService.listByBelong(QZONE.value());

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
}
