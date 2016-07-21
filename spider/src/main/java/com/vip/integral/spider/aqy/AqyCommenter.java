package com.vip.integral.spider.aqy;

import com.alibaba.fastjson.JSON;
import com.vip.integral.bean.Comment;
import com.vip.integral.component.Commenter;

/**
 * Created by lihuajun on 16-7-16.
 */
public class AqyCommenter extends Commenter {

    @Override public Comment comment() {

        System.out.println(JSON.parseObject(action).getString("comment"));

        return null;

    }

    @Override public void reply() {

    }

    @Override public void praise() {

    }

    @Override public void echo() {

    }
}
