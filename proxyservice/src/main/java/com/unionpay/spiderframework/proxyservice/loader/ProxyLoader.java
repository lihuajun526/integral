package com.unionpay.spiderframework.proxyservice.loader;

import java.util.List;

import com.unionpay.spiderframework.service.model.DBStatus;
import com.unionpay.spiderframework.service.service.DBStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.unionpay.spiderframework.proxyservice.cache.ProxyCache;
import com.unionpay.spiderframework.proxyservice.util.SpringContext;
import com.unionpay.spiderframework.service.model.CrawlProxy;
import com.unionpay.spiderframework.service.service.CrawlProxyService;

/**
 * @author: Zhou Xuanang
 * @Date: 16:49 2016/11/8.
 */
public class ProxyLoader {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProxyLoader.class);

    public static void load() {
        LOGGER.info("开始加载代理...");
        ApplicationContext ctx = SpringContext.getContext();
        CrawlProxyService crawlProxyService = (CrawlProxyService) ctx.getBean("crawlProxyService");

        List<CrawlProxy> crawlProxyList = crawlProxyService.getAll();
        for (CrawlProxy crawlProxy : crawlProxyList) {
            if (crawlProxy.getLevel() == 1) {
                ProxyCache.proxyPool.get(1).add(crawlProxy);
                ProxyCache.proxyPoolCopy.get(1).add(crawlProxy);
            } else if (crawlProxy.getLevel() == 2) {
                ProxyCache.proxyPool.get(2).add(crawlProxy);
                ProxyCache.proxyPoolCopy.get(2).add(crawlProxy);
            } else if (crawlProxy.getLevel() == 0) {
                ProxyCache.proxyPool.get(0).add(crawlProxy);
                ProxyCache.proxyPoolCopy.get(0).add(crawlProxy);
            }
        }

        LOGGER.info("代理加载完毕!");

    }

    public static boolean checkDBState() {
        ApplicationContext ctx = SpringContext.getContext();
        DBStatusService dbStatusService = (DBStatusService) ctx.getBean("dBStatusService");
        DBStatus dbStatus = dbStatusService.get();

        /**
         * 0 不需要更新, 1 需要更新
         */

        if (dbStatus.getProxyStatus() == 0)
            return false;

        return true;
    }
}
