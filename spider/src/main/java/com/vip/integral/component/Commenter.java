package com.vip.integral.component;

import com.vip.integral.bean.Comment;
import com.vip.integral.exception.RequestException;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

/**
 * Created by lihuajun on 16-7-16.
 * 评论者
 */
public abstract class Commenter extends Attacker{

    /**
     * 评论
     */
    public abstract Comment comment() throws URISyntaxException, UnsupportedEncodingException, RequestException;

    /**
     * 回复
     */
    public abstract void reply();

    /**
     * 点赞
     */
    public abstract void praise();

    /**
     * 附和
     */
    public abstract void echo();

}
