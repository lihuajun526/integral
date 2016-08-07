package com.vip.integral.attack.qzone.task;

import com.vip.integral.attack.qzone.QZoneCommenter;
import com.vip.integral.bean.SpringContext;
import com.vip.integral.model.AttackPage;
import com.vip.integral.model.AttackParam;
import com.vip.integral.service.AttackParamService;

import java.util.ArrayList;
import java.util.List;

import static com.vip.integral.constant.Belong.QZONE;

/**
 * Created by lihuajun on 2016/8/5.
 */
public class AttackTask implements Runnable {
    @Override
    public void run() {
        {
            // 获得所有的评论水军
            List<QZoneCommenter> commenters = allCommenter();

            // 获得评论详细页
            List<AttackPage> attackPageList = null;

           /* for (int i = 0; i < attackPageList.size(); i++) /*{
                try {
                    // 主角对影片进行评论，所有配角对该评论进行点赞/回复
                    //选一个主角
                    AqyCommenter major = commenters.get(i % commenters.size());
                    major.setAttackPage(attackPageList.get(i));
                    Comment comment = major.comment();
                    //配角点赞/回复
                    for (AqyCommenter support : commenters) {
                        if (support == major) {
                            support.praise(comment);
                            continue;
                        }
                        support.setAttackPage(attackPageList.get(i));
                        support.praise(comment);
                        support.reply(comment);
                    }
                *//*
                // TODO: 16-7-16 主角对最热的前N条评论进行点赞/回复，所有配角对该回复点赞，每次再选两个配角进行附和
                List<Comment> hotComments = major.listHotComment(maxHotCount, maxReply);
                for (int j = 0; j < hotComments.size(); j++) {
                    Comment hot = hotComments.get(j);
                    //主角点赞/回复
                    major.praise(hot);
                    Comment hotReply = major.reply(hot);
                    //所有配角点赞
                    for (AqyCommenter support : commenters) {
                        support.praise(hotReply);
                    }
                    //选N个配角附和
                    for (int m = 1; m <= echoCount; m++) {
                        commenters.get((i + j * 2 + m) % commenters.size()).echo();
                    }
                }*//*
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }*/

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
            commenters.add(qZoneCommenter);
        }

        return commenters;
    }
}
