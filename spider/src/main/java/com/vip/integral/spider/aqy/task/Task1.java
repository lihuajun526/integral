package com.vip.integral.spider.aqy.task;

import com.vip.integral.spider.aqy.AqyObserver;
import com.vip.integral.spider.aqy.CommentTarget;

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
        // TODO: 16-7-16 获得所有的评论水军
        List<AqyObserver> observerList = null;
        // TODO: 16-7-16 获得片源详细页
        List<CommentTarget> commentTargetList = null;
        // TODO: 16-7-16 获得最热的N条评论
        List<Object> hotComments = null;

        for (int i = 0; i < commentTargetList.size(); i++) {
            // TODO: 16-7-16 主角对影片进行评论，所有配角对该评论进行点赞/回复
            //选一个主角
            AqyObserver major = observerList.get(i % observerList.size());
            major.comment();
            //配角点赞/回复
            for (AqyObserver support : observerList) {
                support.praise();
                support.reply();
            }
            // TODO: 16-7-16 主角对最热的前N条评论进行点赞/回复，所有配角对该回复点赞，每次再选两个配角进行附和
            for (int j = 0; j < hotComments.size(); j++) {
                //主角点赞/回复
                major.praise();
                major.reply();
                //所有配角点赞
                for (AqyObserver support : observerList) {
                    support.praise();
                }
                //选N个配角附和
                for (int m = 1; m <= echoCount; m++) {
                    observerList.get((i + j * 2 + m) % observerList.size()).echo();
                }
            }
        }

    }

}
