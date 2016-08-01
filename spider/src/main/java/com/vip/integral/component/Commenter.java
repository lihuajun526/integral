package com.vip.integral.component;

import com.vip.integral.bean.Comment;
import com.vip.integral.exception.RequestException;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by lihuajun on 16-7-16.
 * 评论者
 */
public abstract class Commenter extends Attacker {

    /**
     * 评论
     */
    protected abstract Comment comment() throws URISyntaxException, UnsupportedEncodingException, RequestException;

    /**
     * 回复
     */
    protected abstract Comment reply(Comment comment);

    /**
     * 点赞
     */
    protected abstract void praise(Comment comment) throws RequestException, URISyntaxException;

    /**
     * 附和
     */
    protected abstract void echo();

    /**
     * 获取热门评论
     * @return
     */
    protected abstract List<Comment> listHotComment(int maxComment,int maxReply) throws RequestException;

}
