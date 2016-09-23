package com.vip.spider.component;

import com.vip.spider.bean.Comment;
import com.vip.spider.exception.CommentException;
import com.vip.spider.exception.RequestException;

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
    protected abstract Comment comment()
            throws URISyntaxException, UnsupportedEncodingException, RequestException, CommentException;

    /**
     * 回复
     */
    protected Comment reply(Comment comment) throws CommentException {
        return null;
    }

    /**
     * 点赞
     */
    protected void praise(Comment comment) throws RequestException, URISyntaxException{

    }

    /**
     * 附和
     */
    protected Comment echo(Comment comment) throws CommentException {
        return null;
    }

    /**
     * 获取热门评论
     *
     * @return
     */
    protected List<Comment> listHotComment(int maxComment, int maxReply) throws RequestException{
        return null;
    }

}
