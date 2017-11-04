package com.operational.platform.operate.component.attack;

import com.operational.platform.common.exception.CommonException;
import com.operational.platform.operate.bean.IMessage;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lihuajun on 2017/11/3.
 */
public abstract class Attacker {

    public final static Logger LOGGER = LoggerFactory.getLogger(Attacker.class);

    public HttpGet httpGet = new HttpGet();
    public HttpPost httpPost = new HttpPost();

    public abstract void exe(IMessage iMessage) throws CommonException;

    public abstract void init();

}
