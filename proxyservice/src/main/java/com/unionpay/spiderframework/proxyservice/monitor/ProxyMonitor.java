package com.unionpay.spiderframework.proxyservice.monitor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import com.unionpay.spiderframework.proxyservice.constant.Status;
import com.unionpay.spiderframework.proxyservice.entity.ThreadResult;
import com.unionpay.spiderframework.spiderframework.common.bean.Crawl;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.unionpay.spiderframework.proxyservice.cache.ProxyCache;
import com.unionpay.spiderframework.proxyservice.entity.MonitorQualitySource;
import com.unionpay.spiderframework.service.model.CrawlProxy;
import com.unionpay.spiderframework.service.service.CrawlProxyService;

/**
 * 代理监测 每两分钟重新检测代理,评估质量
 * 
 * @author: Zhou Xuanang
 * @Date: 11:15 2016/11/9.
 */
public class ProxyMonitor {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ProxyMonitor.class);

    private String reachableURL;
    private String monitorQualitySource;
    private CrawlProxyService crawlProxyService;
    private ExecutorService fixedThreadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

    public void execute() {
        Status.MONITOR_STATE = 1;
        LOGGER.info("======>开始检测、评估代理...");
        System.out.println(ProxyCache.proxyPool.get(0).size());
        System.out.println(ProxyCache.proxyPool.get(1).size());
        System.out.println(ProxyCache.proxyPool.get(2).size());
        System.out.println(ProxyCache.proxyPoolCopy.get(0).size());
        System.out.println(ProxyCache.proxyPoolCopy.get(1).size());
        System.out.println(ProxyCache.proxyPoolCopy.get(2).size());

        List<CrawlProxy> proxyList = new ArrayList<CrawlProxy>() {
            {
                for (List<CrawlProxy> list : ProxyCache.proxyPool.values()) {
                    for (CrawlProxy crawlProxy : list) {
                        add(crawlProxy.clone());
                    }
                }
            }
        };

        // 清空代理内存
        ProxyCache.clear();

        List<JSONObject> qualitySourceList = JSON.toJavaObject(JSONObject.parseArray(monitorQualitySource),
                List.class);

        List<Future> futureList = new ArrayList<>();
        for (CrawlProxy crawlProxy : proxyList) {
            Callable c = new TestReachable(crawlProxy, reachableURL);
            Future f = fixedThreadPool.submit(c);
            futureList.add(f);
        }
        proxyList.clear();
        try {
            for (Future future : futureList) {
                try {
                    proxyList.add((CrawlProxy) ((ThreadResult) future.get()).getResult());
                } catch (NullPointerException e) {
                    continue;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        for (CrawlProxy crawlProxy : proxyList) {
            List<Future> futureList1 = new ArrayList<>();
            CrawlProxy tempCrawlProxy = crawlProxy.clone();
            for (JSONObject object : qualitySourceList) {
                MonitorQualitySource monitorQualitySource = JSONObject.toJavaObject(object, MonitorQualitySource.class);
                Callable c = new TestQuality(monitorQualitySource, tempCrawlProxy);
                Future f = fixedThreadPool.submit(c);
                futureList1.add(f);
            }

            try {
                try {
                    boolean flag = true;
                    for (Future future : futureList1) {
                        ThreadResult threadResult;
                        try {
                            threadResult = (ThreadResult) future.get();
                        } catch (NullPointerException e) {
                            flag = false;
                            break;
                        }
                        if (!(boolean) threadResult.getResult()) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        crawlProxy.setLevel(1);
                        crawlProxyService.update(crawlProxy);
                        ProxyCache.proxyPool.get(1).add(crawlProxy.clone());
                        LOGGER.info("[id:{}]代理等级修改为1", crawlProxy.getId());

                    } else {
                        crawlProxy.setLevel(2);
                        crawlProxyService.update(crawlProxy);
                        ProxyCache.proxyPool.get(2).add(crawlProxy.clone());
                        LOGGER.info("[id:{}]代理等级修改为2", crawlProxy.getId());
                    }
                } catch (NullPointerException e) {
                    continue;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        ProxyCache.copyCache();
        LOGGER.info("======>代理检测、评估完成...");
        Status.MONITOR_STATE = 0;
    }

    public void setReachableURL(String reachableURL) {
        this.reachableURL = reachableURL;
    }

    public String getReachableURL() {
        return reachableURL;
    }

    public String getMonitorQualitySource() {
        return monitorQualitySource;
    }

    public void setMonitorQualitySource(String monitorQualitySource) {
        this.monitorQualitySource = monitorQualitySource;
    }

    public CrawlProxyService getCrawlProxyService() {
        return crawlProxyService;
    }

    public void setCrawlProxyService(CrawlProxyService crawlProxyService) {
        this.crawlProxyService = crawlProxyService;
    }
}
