package com.vip.integral.spider.aqy.task;

import com.vip.integral.bean.Comment;
import com.vip.integral.bean.SpringContext;
import com.vip.integral.model.AttackParam;
import com.vip.integral.model.PageLink;
import com.vip.integral.service.AttackParamService;
import com.vip.integral.service.PageLinkService;
import com.vip.integral.spider.aqy.AqyCommenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihuajun on 16-7-16.
 */
public class Task1 {

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
    public void exe() {
        // 获得所有的评论水军
        List<AqyCommenter> commenters = allCommenter();
        // 获得评论详细页
        List<PageLink> pageLinkList = listPageLink();

        try {
            for (int i = 0; i < pageLinkList.size(); i++) {
                // 主角对影片进行评论，所有配角对该评论进行点赞/回复
                //选一个主角
                AqyCommenter major = commenters.get(i % commenters.size());
                Comment comment = major.comment();
                //配角点赞/回复
                for (AqyCommenter support : commenters) {
                    support.praise(comment);
                    if (support == major)
                        continue;
                    support.reply(comment);
                }
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
    private List<AqyCommenter> allCommenter() {

        List<AqyCommenter> commenters = new ArrayList<>();

        AttackParamService attackParamService = (AttackParamService) SpringContext.getContext().getBean("attackerService");
        List<AttackParam> list = attackParamService.listByBelong("aqy");

        for (AttackParam attackParam : list) {
            AqyCommenter aqyCommenter = new AqyCommenter();
            //aqyCommenter.setAttackParam(attackParam);
            commenters.add(aqyCommenter);
        }

        return commenters;
    }

    /**
     * 获得所有评论目标
     *
     * @return
     */
    private List<PageLink> listPageLink() {

        PageLinkService pageLinkService = (PageLinkService) SpringContext.getContext().getBean("pageLinkService");
        List<PageLink> list = pageLinkService.listByBelong("aqy");
        return list;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("123");
        list.add("abc");

        String str1 = list.get(0);

        String str2 = list.get(0);

        System.out.println(str1 == str2);

    }

}
