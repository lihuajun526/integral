package com.vip.spider.attack.aqy.task;

import com.vip.spider.constant.Belong;
import com.vip.spider.bean.Comment;
import com.vip.spider.bean.SpringContext;
import com.vip.dbservice.model.AttackPage;
import com.vip.dbservice.model.AttackParam;
import com.vip.dbservice.service.AttackPageService;
import com.vip.dbservice.service.AttackParamService;
import com.vip.spider.attack.aqy.AqyCommenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihuajun on 16-7-16.
 */
public class AttackTask implements Runnable {

    //热评最大数
    private int maxHotCount = 3;
    //最大回复数
    private int maxReply = 3;
    //附和的配角数
    private int echoCount = 2;

    /**
     * uid,antiCsrf
     * 这些参数要初始化
     */
    @Override
    public void run() {
        // 获得所有的评论水军
        List<AqyCommenter> commenters = allCommenter();
        // 获得评论详细页
        List<AttackPage> attackPageList = listAttackPage();

        for (int i = 0; i < attackPageList.size(); i++) {
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
                /*
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
                }*/
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 获得所有评论者
     *
     * @return
     */
    private List<AqyCommenter> allCommenter() {

        List<AqyCommenter> commenters = new ArrayList<>();

        AttackParamService attackParamService = (AttackParamService) SpringContext.getContext().getBean("attackParamService");
        List<AttackParam> list = attackParamService.listByBelong(Belong.AQY.value());

        for (AttackParam attackParam : list) {
            AqyCommenter aqyCommenter = new AqyCommenter();
            aqyCommenter.setAttackParam(attackParam);
            commenters.add(aqyCommenter);
        }

        return commenters;
    }

    /**
     * 获得所有评论目标
     *
     * @return
     */
    private List<AttackPage> listAttackPage() {

        AttackPageService attackPageService = (AttackPageService) SpringContext.getContext().getBean("attackPageService");
        List<AttackPage> list = attackPageService.listByBelong(Belong.AQY.value());
        return list;
    }

}
