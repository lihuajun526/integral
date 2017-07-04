package com.unionpay.spiderframework.proxyservice.monitor;

import java.util.concurrent.Callable;

import com.unionpay.spiderframework.proxyservice.entity.ThreadResult;
import org.apache.http.HttpHost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.unionpay.common.utils.http.HttpClientException;
import com.unionpay.common.utils.http.Request;
import com.unionpay.common.utils.http.SimpleHttpExecutor;
import com.unionpay.spiderframework.proxyservice.util.SpringContext;
import com.unionpay.spiderframework.service.model.CrawlProxy;
import com.unionpay.spiderframework.service.service.CrawlProxyService;

/**
 * @author: Zhou Xuanang
 * @Date: 14:45 2016/11/9.
 */
public class TestReachable implements Callable {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestReachable.class);

    private CrawlProxy proxy;
    private String url;
    private SimpleHttpExecutor httpExecutor = SimpleHttpExecutor.newInstance();
    private CrawlProxyService crawlProxyService = (CrawlProxyService) SpringContext.getContext().getBean("crawlProxyService");

    public TestReachable(CrawlProxy proxy, String url) {
        this.proxy = proxy;
        this.url = url;
    }

    @Override
    public Object call() {
        httpExecutor.setDefaultProxy(new HttpHost(proxy.getIp(), Integer.parseInt(proxy.getPort())));
        try {
            httpExecutor.request(Request.Get(url));
        } catch (HttpClientException e) {
            LOGGER.info("[id:{}]代理不通!", proxy.getId());
            crawlProxyService.delete(proxy.getId());
            return new ThreadResult(e);
        }
        return new ThreadResult(proxy);
    }
}
