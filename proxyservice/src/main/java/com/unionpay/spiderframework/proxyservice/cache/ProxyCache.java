package com.unionpay.spiderframework.proxyservice.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.unionpay.spiderframework.service.model.CrawlProxy;

/**
 * @author: Zhou Xuanang
 * @Date: 09:38 2016/11/9.
 */
public class ProxyCache {
    public static Map<Integer, List<CrawlProxy>> proxyPool = new ConcurrentHashMap<Integer, List<CrawlProxy>>() {
        {
            put(0, new ArrayList<>());
            put(1, new ArrayList<>());
            put(2, new ArrayList<>());
        }
    };
    public static Map<Integer, List<CrawlProxy>> proxyPoolCopy = new ConcurrentHashMap<Integer, List<CrawlProxy>>() {
        {
            put(0, new ArrayList<>());
            put(1, new ArrayList<>());
            put(2, new ArrayList<>());
        }
    };

    public static void clear() {
        proxyPool.get(0).clear();
        proxyPool.get(1).clear();
        proxyPool.get(2).clear();
    }

    public static void copyCache() {
        List<CrawlProxy> level0List = new ArrayList<>();
        List<CrawlProxy> level1List = new ArrayList<>();
        List<CrawlProxy> level2List = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            for (CrawlProxy crawlProxy : proxyPool.get(i)) {
                if (i == 0) {
                    level0List.add(crawlProxy);
                } else if (i == 1) {
                    level1List.add(crawlProxy);
                } else if (i == 2) {
                    level2List.add(crawlProxy);
                }
            }
        }
        proxyPoolCopy.get(1).clear();
        proxyPoolCopy.get(1).addAll(level0List);
        proxyPoolCopy.get(2).clear();
        proxyPoolCopy.get(2).addAll(level1List);
        proxyPoolCopy.get(3).clear();
        proxyPoolCopy.get(3).addAll(level2List);

    }
}
