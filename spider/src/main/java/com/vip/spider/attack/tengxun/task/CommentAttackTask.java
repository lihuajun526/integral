package com.vip.spider.attack.tengxun.task;

import com.vip.spider.attack.tengxun.TxxwCommenter;
import com.vip.spider.bean.SpringContext;
import com.vip.spider.constant.Belong;
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
public class CommentAttackTask implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentAttackTask.class);

    @Override
    public void run() {
        {
            // 获得所有的评论水军
            List<TxxwCommenter> commenters = allCommenter();

            // 获得评论详细页
            List<AttackPage> attackPageList = listAttackPage();

            for (int i = 0; i < attackPageList.size(); i++) {

                try {
                    AttackPage attackPage = attackPageList.get(i);
                    TxxwCommenter fixCommenter = commenters.get(0);
                    fixCommenter.setAttackPage(attackPage);
                    //评论
                    fixCommenter.comment();
                    //
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
    private List<TxxwCommenter> allCommenter() {

        List<TxxwCommenter> commenters = new ArrayList<>();

        AttackParamService attackParamService = (AttackParamService) SpringContext.getContext().getBean("attackParamService");
        List<AttackParam> list = attackParamService.listByBelong(Belong.TXXW.value());

        for (AttackParam attackParam : list) {
            TxxwCommenter txxwCommenter = new TxxwCommenter();
            txxwCommenter.setAttackParam(attackParam);
            commenters.add(txxwCommenter);
        }

        return commenters;
    }

    /**
     * 获得评论目标
     *
     * @return
     */
    private List<AttackPage> listAttackPage() {

        AttackPageService attackPageService = (AttackPageService) SpringContext.getContext().getBean("attackPageService");
        //List<AttackPage> list = attackPageService.listByBelong(TXXW.value());
        List<AttackPage> list = new ArrayList<>();
        list.add(attackPageService.selectByPrimaryKey(33778));
        return list;
    }
}
