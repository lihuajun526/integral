package com.operational.platform.spider.attack.tengxun.task;

import com.alibaba.fastjson.JSONObject;
import com.operational.platform.common.util.Config;
import com.operational.platform.spider.attack.tengxun.TxxwCommenter;
import com.operational.platform.spider.attack.tengxun.bean.TxxwComment;
import com.operational.platform.spider.bean.SpringContext;
import com.operational.platform.spider.constant.Belong;
import com.operational.platform.spider.bean.Comment;
import com.operational.platform.dbservice.model.AttackPage;
import com.operational.platform.dbservice.model.AttackParam;
import com.operational.platform.dbservice.service.AttackPageService;
import com.operational.platform.dbservice.service.AttackParamService;
import com.operational.platform.spider.util.XHttpClient;
import org.apache.http.client.methods.HttpGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihuajun on 2016/8/5.
 */
public class ReplyAttackTask implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReplyAttackTask.class);
    private int maxReply = 2;

    @Override
    public void run() {
        // 获得所有的评论水军
        List<TxxwCommenter> commenters = allCommenter();
        String txxwCommenterId = Config.get("txxw.commenter.id");

        String hotcommentUrlTpl = "http://coral.qq.com/article/%s/hotcomment?reqnum=10";
        String commentUrlTpl = "http://coral.qq.com/article/%s/comment?commentid=0&reqnum=10";

        List<AttackPage> attackPageList = listAttackPage();

        for (int i = 0; i < attackPageList.size(); i++) {

            try {
                AttackPage attackPage = attackPageList.get(i);
                TxxwCommenter replyer = commenters.get(1);//回复者
                TxxwCommenter echoer = commenters.get(2);//附和者
                replyer.setAttackPage(attackPage);
                echoer.setAttackPage(attackPage);
                //获取热评
                JSONObject attr = JSONObject.parseObject(attackPage.getAttr());
                String hotcommentUrl = String.format(hotcommentUrlTpl, attr.getString("commentid"));
                String commentUrl = String.format(commentUrlTpl, attr.getString("commentid"));
                HttpGet httpGet = new HttpGet();
                httpGet.setURI(new URI(hotcommentUrl));
                String result = XHttpClient.doRequest(httpGet);
                TxxwComment txxwComment = JSONObject.parseObject(result, TxxwComment.class);
                List<TxxwComment.Data.Commentid> txxwCommentList = txxwComment.getData().getCommentid();
                if (txxwCommentList == null || txxwCommentList.size() == 0) {//如果没有热评，则获取所有评论
                    httpGet.setURI(new URI(commentUrl));
                    result = XHttpClient.doRequest(httpGet);
                    txxwComment = JSONObject.parseObject(result, TxxwComment.class);
                    txxwCommentList = txxwComment.getData().getCommentid();
                }
                //回复前N条评论
                for (int j = 0, count = 0; j < txxwCommentList.size() && (j < 3 || count < maxReply); j++) {
                    TxxwComment.Data.Commentid commentid = txxwCommentList.get(j);
                    if ("0".equals(commentid.getParent()) && "0".equals(commentid.getIsdeleted())) {
                        Comment comment = new Comment();
                        comment.setId(commentid.getId());
                        //碰到自己人的评论果断附和
                        if (txxwCommenterId.equals(commentid.getUserid())) {
                            echoer.echo(comment);
                            echoer.praise(comment);
                            continue;
                        }
                        replyer.reply(comment);
                        replyer.praise(comment);
                        count++;
                    }
                }
            } catch (Exception e) {
                LOGGER.error("回复失败:", e);
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
