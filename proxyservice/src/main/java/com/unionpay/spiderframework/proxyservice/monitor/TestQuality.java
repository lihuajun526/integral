package com.unionpay.spiderframework.proxyservice.monitor;

import java.util.concurrent.Callable;

import com.unionpay.spiderframework.proxyservice.entity.ThreadResult;
import org.apache.http.HttpHost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.unionpay.common.utils.http.HttpClientException;
import com.unionpay.common.utils.http.Request;
import com.unionpay.common.utils.http.SimpleHttpExecutor;
import com.unionpay.spiderframework.proxyservice.entity.MonitorQualitySource;
import com.unionpay.spiderframework.proxyservice.util.SpringContext;
import com.unionpay.spiderframework.service.model.CrawlProxy;
import com.unionpay.spiderframework.service.service.CrawlProxyService;

/**
 * @author: Zhou Xuanang
 * @Date: 15:22 2016/11/9.
 */
public class TestQuality implements Callable<Object> {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestQuality.class);

    private MonitorQualitySource monitorQualitySource;
    private CrawlProxy proxy;
    private SimpleHttpExecutor httpExecutor = SimpleHttpExecutor.newInstance();
    private CrawlProxyService crawlProxyService = (CrawlProxyService) SpringContext.getContext().getBean("crawlProxyService");

    public TestQuality(MonitorQualitySource monitorQualitySource, CrawlProxy proxy) {
        this.monitorQualitySource = monitorQualitySource;
        this.proxy = proxy;
    }

    @Override
    public Object call() {
        try {
            httpExecutor.setDefaultProxy(new HttpHost(proxy.getIp(), Integer.parseInt(proxy.getPort())));
            Long start = System.currentTimeMillis();
            httpExecutor.request(Request.Get(monitorQualitySource.getUrl()));
            Long end = System.currentTimeMillis();
            if (Integer.toUnsignedLong(monitorQualitySource.getExpectResponseSeconds() * 1000) > (start - end)) {
                return new ThreadResult(true);
            }
            return new ThreadResult(false);

        } catch (HttpClientException e) {
            LOGGER.info("[id:{}]代理不通!", proxy.getId());
            crawlProxyService.delete(proxy.getId());
            return new ThreadResult(e);
        }

    }
}
