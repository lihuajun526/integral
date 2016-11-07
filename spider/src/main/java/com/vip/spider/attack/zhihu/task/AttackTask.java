package com.vip.spider.attack.zhihu.task;

import com.alibaba.fastjson.JSON;
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
            List<ZhihuMessageSender> senders = allSender();

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
                    ZhihuMessageSender fixSender = getSenderBySex(senders, sex);
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
    private List<ZhihuMessageSender> allSender() {

        List<ZhihuMessageSender> senders = new ArrayList<>();

        AttackParamService attackParamService = (AttackParamService) SpringContext.getContext().getBean("attackParamService");
        List<AttackParam> list = attackParamService.listByBelong(Belong.ZHIHU.value());

        for (AttackParam attackParam : list) {
            ZhihuMessageSender zhihuMessageSender = new ZhihuMessageSender(attackPageService);
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
    private ZhihuMessageSender getSenderBySex(List<ZhihuMessageSender> senders, Integer sex) {

        List<ZhihuMessageSender> tempList = new ArrayList<>();
        for (ZhihuMessageSender sender : senders) {
            JSONObject attr = JSON.parseObject(sender.getAttackParam().getAttr());
            if (attr.getInteger("sex") == sex)
                tempList.add(sender);
        }

        Random random = new Random();
        int index = random.nextInt(tempList.size());

        return senders.get(index);
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
