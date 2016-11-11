package com.operational.platform.spider.attack.zhihu.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.operational.platform.spider.attack.zhihu.MessageSender;
import com.operational.platform.dbservice.model.AttackPage;
import com.operational.platform.dbservice.model.AttackParam;
import com.operational.platform.dbservice.service.AttackPageService;
import com.operational.platform.dbservice.service.AttackParamService;
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
public class AttackTask implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(AttackTask.class);

    private AttackPageService attackPageService;

    public AttackTask(AttackPageService attackPageService) {
        this.attackPageService = attackPageService;
    }

    @Override
    public void run() {
        {
            // 获得所有消息发送者
            List<MessageSender> senders = allSender();

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
                    MessageSender fixSender = getSenderBySex(senders, sex);
                    //发送信息
                    fixSender.setAttackPage(attackPage);
                    fixSender.send();
                }
            } catch (Exception e) {
                LOGGER.error("信息发送失败:", e);
            }
        }
    }

    /**
     * 获得所有评论者
     *
     * @return
     */
    private List<MessageSender> allSender() {

        List<MessageSender> senders = new ArrayList<>();

        AttackParamService attackParamService = (AttackParamService) SpringContext.getContext().getBean("attackParamService");
        List<AttackParam> list = attackParamService.listByBelong(Belong.ZHIHU.value());

        for (AttackParam attackParam : list) {
            MessageSender zhihuMessageSender = new MessageSender(attackPageService);
            zhihuMessageSender.setAttackParam(attackParam);
            senders.add(zhihuMessageSender);
        }

        return senders;
    }

    /**
     * 选择异性发送消息
     *
     * @param senders
     * @param sex
     * @return
     */
    private MessageSender getSenderBySex(List<MessageSender> senders, Integer sex) {

        List<MessageSender> tempList = new ArrayList<>();
        for (MessageSender sender : senders) {
            JSONObject attr = JSON.parseObject(sender.getAttackParam().getAttr());
            if (attr.getInteger("sex") == sex)
                tempList.add(sender);
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
