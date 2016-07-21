package com.vip.integral;

import com.vip.integral.bean.CrawlPointAttr;
import com.vip.integral.bean.SpringContext;
import com.vip.integral.component.creater.PointLinkCreater;
import com.vip.integral.model.CrawlPoint;
import com.vip.integral.service.CrawlPointService;
import com.vip.integral.task.CrawlTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lihuajun on 16-7-19.
 */
public class CrawlApp {

    private static final Logger Logger = LoggerFactory.getLogger(CrawlApp.class);

    public static void main(String[] args) {

        //初始化
        SpringContext.init("classpath:spring/spring.xml");

        //构建采集点规则集合
        List<CrawlPointAttr> list = listCrawlPointAttr();

        //采集
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (CrawlPointAttr crawlPointAttr : list) {
            fixedThreadPool.execute(new CrawlTask(crawlPointAttr));
        }

    }

    //获取采集点属性集合
    private static List<CrawlPointAttr> listCrawlPointAttr() {
        List<CrawlPointAttr> list = new ArrayList<>();
        CrawlPointService CrawlPointService = (CrawlPointService) SpringContext.getContext().getBean("crawlPointService");
        List<CrawlPoint> crawlPointList = CrawlPointService.list();
        for (CrawlPoint crawlPoint : crawlPointList) {

            List<String> linkList = new ArrayList<>();
            if (!StringUtils.isEmpty(crawlPoint.getUrlCrClassPath())) {//采集点是集合
                try {
                    PointLinkCreater pointLinkCreater = (PointLinkCreater) Class.forName(crawlPoint.getUrlCrClassPath())
                            .newInstance();
                    linkList.addAll(pointLinkCreater.get());
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                    Logger.error("加载类[" + crawlPoint.getUrlCrClassPath() + "]失败", e);
                }
            } else {
                linkList.add(crawlPoint.getUrl());
            }
            //组装采集点属性集合
            for (String link : linkList) {
                CrawlPointAttr crawlPointAttr = new CrawlPointAttr();
                crawlPointAttr.setUrl(crawlPoint.getUrl());
                list.add(crawlPointAttr);
            }
        }
        return list;
    }

}
