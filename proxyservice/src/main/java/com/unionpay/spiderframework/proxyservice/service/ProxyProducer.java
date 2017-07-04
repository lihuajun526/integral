package com.unionpay.spiderframework.proxyservice.service;

import java.util.ArrayList;
import java.util.List;

import com.unionpay.spiderframework.proxyservice.cache.ProxyCache;
import com.unionpay.spiderframework.service.model.CrawlProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 提供代理接口
 * 
 * @author: Zhou Xuanang
 * @Date: 13:32 2016/11/10.
 */
public class ProxyProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProxyProducer.class);

    public static List<CrawlProxy> provide() {
        List<CrawlProxy> list = new ArrayList<>();

        if (ProxyCache.proxyPoolCopy.get(1).size() != 0) {
            for (CrawlProxy crawlProxy : ProxyCache.proxyPoolCopy.get(1)) {
                list.add(crawlProxy.clone());
            }
            LOGGER.info("提供代理1级代理[{}]个.", list.size());
            return list;
        } else if (ProxyCache.proxyPoolCopy.get(2).size() != 0) {
            for (CrawlProxy crawlProxy : ProxyCache.proxyPoolCopy.get(2)) {
                list.add(crawlProxy.clone());
            }
            LOGGER.info("提供代理2级代理[{}]个.", list.size());
            return list;
        } else {
            if (ProxyCache.proxyPoolCopy.get(0).size() != 0) {
                for (CrawlProxy crawlProxy : ProxyCache.proxyPoolCopy.get(0)) {
                    list.add(crawlProxy.clone());
                }
                LOGGER.info("提供代理0级代理[{}]个.", list.size());
                return list;
            }
        }
        LOGGER.info("暂无可用代理.");
        return list;
    }
}
