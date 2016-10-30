package com.vip.spider.attack.zhihu.task;

import com.alibaba.fastjson.JSONObject;
import com.vip.dbservice.model.AttackPage;
import com.vip.dbservice.model.AttackParam;
import com.vip.dbservice.service.AttackPageService;
import com.vip.dbservice.service.AttackParamService;
import com.vip.spider.attack.qzone.bean.AttackAttr;
import com.vip.spider.attack.zhihu.ZhihuMessageSender;
import com.vip.spider.bean.SpringContext;
import com.vip.spider.constant.Belong;
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
            // 获得所有消息发送者
            List<ZhihuMessageSender> senders = allSender();

            // 获得详细页
            List<AttackPage> attackPageList = listAttackPage();

            for (int i = 0; i < attackPageList.size(); i++) {
                AttackPage attackPage = attackPageList.get(i);
                try {
                    ZhihuMessageSender fixSender = getSenderBySex(senders, 0);
                    //发送信息
                    fixSender.setAttackPage(attackPage);
                    fixSender.send();
                } catch (Exception e) {
                    LOGGER.error("信息发送失败:", e);
                }
            }

        }
    }

    /**
     * 获得所有评论者
     *
     * @return
     */
    private List<ZhihuMessageSender> allSender() {

        List<ZhihuMessageSender> senders = new ArrayList<>();

        AttackParamService attackParamService = (AttackParamService) SpringContext.getContext().getBean("attackParamService");
        List<AttackParam> list = attackParamService.listByBelong(Belong.ZHIHU.value());

        for (AttackParam attackParam : list) {
            ZhihuMessageSender zhihuMessageSender = new ZhihuMessageSender();
            zhihuMessageSender.setAttackParam(attackParam);
            senders.add(zhihuMessageSender);
        }

        return senders;
    }

    /**
     * 选择异性发送消息
     * @param senders
     * @param sex
     * @return
     */
    private ZhihuMessageSender getSenderBySex(List<ZhihuMessageSender> senders, Integer sex) {

        return senders.get(0);
    }

    /**
     * 获得信息发送目标
     *
     * @return
     */
    private List<AttackPage> listAttackPage() {

        AttackPageService attackPageService = (AttackPageService) SpringContext.getContext().getBean("attackPageService");
        List<AttackPage> attackPageList = attackPageService.listByBelong(Belong.ZHIHU.value());
        List<AttackPage> list = new ArrayList<>();
        for (AttackPage attackPage : attackPageList) {
            AttackAttr attackAttr = JSONObject.parseObject(attackPage.getAttr(), AttackAttr.class);
            attackPage.setLink("https://www.zhihu.com/people/zoe-50-36-42");
            list.add(attackPage);
            break;
        }

        return list;
    }
}
