package com.vip.integral.spider.aqy.task;

import com.vip.integral.bean.SpringContext;
import com.vip.integral.model.AttackParam;
import com.vip.integral.model.PageLink;
import com.vip.integral.service.AttackParamService;
import com.vip.integral.service.PageLinkService;
import com.vip.integral.spider.aqy.AqyCommentPage;
import com.vip.integral.spider.aqy.AqyCommenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihuajun on 16-7-16.
 */
public class Task1 {

    //热评最大数
    private int maxHotCount = 3;
    //附和的配角数
    private int echoCount = 2;

    public void exe() {
        // 获得所有的评论水军
        List<AqyCommenter> commenters = allObserver();
        // 获得评论详细页
        List<AqyCommentPage> commentPages = allCommentPage();

        try {
            for (int i = 0; i < commentPages.size(); i++) {
                AqyCommentPage commentPage = commentPages.get(i);
                // TODO: 16-7-16 主角对影片进行评论，所有配角对该评论进行点赞/回复
                //选一个主角
                AqyCommenter major = commenters.get(i % commenters.size());
                major.setAttackPage(commentPage);
                major.init();
                major.comment();
                //配角点赞/回复
                for (AqyCommenter support : commenters) {
                    support.praise();
                    support.reply();
                }
                // TODO: 16-7-16 主角对最热的前N条评论进行点赞/回复，所有配角对该回复点赞，每次再选两个配角进行附和
                for (int j = 0; j < commentPage.hotComments(maxHotCount).size(); j++) {
                    //主角点赞/回复
                    major.praise();
                    major.reply();
                    //所有配角点赞
                    for (AqyCommenter support : commenters) {
                        support.praise();
                    }
                    //选N个配角附和
                    for (int m = 1; m <= echoCount; m++) {
                        commenters.get((i + j * 2 + m) % commenters.size()).echo();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 获得所有评论者
     *
     * @return
     */
    private List<AqyCommenter> allObserver() {

        List<AqyCommenter> observerList = new ArrayList<>();

        AttackParamService attackParamService = (AttackParamService) SpringContext.getContext().getBean("attackerService");
        List<AttackParam> list = attackParamService.listByBelong("aqy");

        for (AttackParam attackParam : list) {
            AqyCommenter aqyObserver = new AqyCommenter();
            aqyObserver.setAttackParam(attackParam);
            observerList.add(aqyObserver);
        }

        return observerList;
    }

    /**
     * 获得所有评论目标
     *
     * @return
     */
    private List<AqyCommentPage> allCommentPage() {

        List<AqyCommentPage> commentTargetList = new ArrayList<>();

        PageLinkService pageLinkService = (PageLinkService) SpringContext.getContext().getBean("pageLinkService");
        List<PageLink> list = pageLinkService.listByBelong("aqy");

        for (PageLink pageLink : list) {
            AqyCommentPage aqyCommentTarget = new AqyCommentPage();
            aqyCommentTarget.setPageLink(pageLink);
            commentTargetList.add(aqyCommentTarget);
        }

        return commentTargetList;
    }

}
