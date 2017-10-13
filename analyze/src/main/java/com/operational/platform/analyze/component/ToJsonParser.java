package com.operational.platform.analyze.component;

import com.operational.platform.analyze.exception.RequestLimitException;
import com.operational.platform.common.bean.MQCrawlJob;
import com.operational.platform.common.exception.CommonException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lihuajun on 2017/9/28.
 */
public abstract class ToJsonParser {

    protected static final Logger LOGGER = LoggerFactory.getLogger(ToJsonParser.class);
    protected HttpGet httpGet;
    protected HttpPost httpPost;

    public abstract void exe(MQCrawlJob crawlJob) throws CommonException, RequestLimitException;

}
