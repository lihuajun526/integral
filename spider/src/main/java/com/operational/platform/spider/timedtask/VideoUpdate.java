package com.operational.platform.spider.timedtask;

import com.operational.platform.dbservice.model.CrawlPoint;
import com.operational.platform.dbservice.model.CrawlPointExample;
import com.operational.platform.dbservice.service.CrawlPointService;
import com.operational.platform.dbservice.service.ScoreService;
import com.operational.platform.dbservice.service.VideoCommentsAndTagsService;
import com.operational.platform.spider.CrawlTask;
import com.operational.platform.spider.bean.CrawlPointAttr;
import com.operational.platform.spider.bean.SpringContext;
import com.operational.platform.spider.component.creater.PointLinkCreater;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.lang.reflect.Constructor;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by lihuajun on 16-7-19.
 */
public class VideoUpdate {

    private static final Logger LOGGER = LoggerFactory.getLogger(VideoUpdate.class);

    public static void execute() {

        LOGGER.info("=====================>开始执行任务");
        /*List<Integer> ids = new ArrayList<>(Arrays.asList(31, 32));
        List<CrawlPointAttr> list = listCrawlPointAttr(ids);
        //采集
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
        for (int i = 1; i <= list.size(); i++) {
            CrawlPointAttr crawlPointAttr = list.get(i - 1);
            crawlPointAttr.setTaskid("Task" + i);
            if (!StringUtils.isEmpty(crawlPointAttr.getTaskClasspath())) {
                try {
                    Class clazz = Class.forName(crawlPointAttr.getTaskClasspath());
                    Constructor constructor = clazz.getConstructor(CrawlPointAttr.class);
                    Runnable task = (Runnable) constructor.newInstance(crawlPointAttr);
                    new Thread(task).start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                fixedThreadPool.execute(new CrawlTask(crawlPointAttr));
            }
        }
        fixedThreadPool.shutdown();
        try {
            fixedThreadPool.awaitTermination(3, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("=====================>所有爬取任务都已完成");
        LOGGER.info("=====================>开始执行评分任务");*/
        ScoreService scoreService = (ScoreService) SpringContext.getContext().getBean("scoreService");
        VideoCommentsAndTagsService videoCommentsAndTagsService = (VideoCommentsAndTagsService) SpringContext.getContext().getBean("videoCommentsAndTagsService");

        //scoreService.score(ids);
        videoCommentsAndTagsService.add();
        LOGGER.info("=====================>评分结束");
    }

    //获取采集点属性集合
    private static List<CrawlPointAttr> listCrawlPointAttr(List<Integer> ids) {

        List<CrawlPointAttr> list = new ArrayList<>();
        CrawlPointService CrawlPointService = (CrawlPointService) SpringContext.getContext().getBean("crawlPointService");

        //设置查询条件
        CrawlPointExample example = new CrawlPointExample();
        CrawlPointExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);

        List<CrawlPoint> crawlPointList = CrawlPointService.listByExample(example);
        for (CrawlPoint crawlPoint : crawlPointList) {
            List<Map<String, String>> linkAttrList = new ArrayList<>();
            if (!StringUtils.isEmpty(crawlPoint.getUrlCrClasspath())) {//采集点是集合
                try {
                    PointLinkCreater pointLinkCreater = (PointLinkCreater) Class.forName(crawlPoint.getUrlCrClasspath())
                            .newInstance();
                    linkAttrList.addAll(pointLinkCreater.get(crawlPoint.getCategory()));
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                    LOGGER.error("加载类[" + crawlPoint.getUrlCrClasspath() + "]失败", e);
                }
            } else {
                Map<String, String> linkAttr = new HashMap<>();
                linkAttr.put("link", crawlPoint.getUrl());
                linkAttrList.add(linkAttr);
            }
            //组装采集点属性集合
            for (Map<String, String> linkAttr : linkAttrList) {
                CrawlPointAttr crawlPointAttr = new CrawlPointAttr();

                crawlPointAttr.setUrl(linkAttr.get("link"));
                crawlPointAttr.setId(crawlPoint.getId());
                crawlPointAttr.setCategory(
                        crawlPoint.getCategory() + (linkAttr.get("index") == null ? "" : "[" + linkAttr.get("index") + "]"));
                crawlPointAttr.setUrlCrClassPath(crawlPoint.getUrlCrClasspath());
                if (crawlPoint.getIsCrawlDetail() != null)
                    crawlPointAttr.setCrawlDetail(crawlPoint.getIsCrawlDetail() == 1 ? true : false);
                crawlPointAttr.setJsonAnalyzePath(crawlPoint.getJsonAnalyzePath());
                crawlPointAttr.setStatus(crawlPoint.getStatus());
                crawlPointAttr.setBelong(crawlPoint.getBelong());
                crawlPointAttr.setMaxPage(crawlPoint.getMaxPage());
                crawlPointAttr.setMethod(crawlPoint.getMethod());
                crawlPointAttr.setPostParam(crawlPoint.getPostParam());
                crawlPointAttr.setHeader(crawlPoint.getHeader());
                crawlPointAttr.setCookies(crawlPoint.getCookies());
                crawlPointAttr.setSleepTime(crawlPoint.getSleepTime());
                crawlPointAttr.setReferer(crawlPoint.getReferer());
                crawlPointAttr.setAccept(crawlPoint.getAccept());
                crawlPointAttr.setPageRule(crawlPoint.getPageRule());
                crawlPointAttr.setResponseEncode(crawlPoint.getResponseEncode());
                crawlPointAttr.setResponseType(crawlPoint.getResponseType());
                crawlPointAttr.setResponseHandler(crawlPoint.getResponseHandler());
                crawlPointAttr.setListRecordRule(crawlPoint.getListRecordRule());
                crawlPointAttr.setListAttrRule(crawlPoint.getListAttrRule());
                crawlPointAttr.setLinkRule(crawlPoint.getLinkRule());
                crawlPointAttr.setLinkSelfRule(crawlPoint.getLinkSelfRule());
                crawlPointAttr.setPageIndexClassPath(crawlPoint.getPageIndexClasspath());
                crawlPointAttr.setPageIndexRule(crawlPoint.getPageIndexRule());
                crawlPointAttr.setAttr(crawlPoint.getAttr());
                crawlPointAttr.setTaskClasspath(crawlPoint.getTaskClasspath());

                list.add(crawlPointAttr);
            }
        }
        return list;
    }

}
