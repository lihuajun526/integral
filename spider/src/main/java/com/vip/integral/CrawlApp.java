package com.vip.integral;

import com.vip.integral.bean.CrawlPointAttr;
import com.vip.integral.bean.SpringContext;
import com.vip.integral.component.creater.PointLinkCreater;
import com.vip.integral.model.CrawlPoint;
import com.vip.integral.service.CrawlPointService;
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
        //设置查询条件
        CrawlPointAttr queryAttr = new CrawlPointAttr();
        queryAttr.setBelong("aqy");

        List<CrawlPointAttr> list = listCrawlPointAttr(queryAttr);

        //采集
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
        for (CrawlPointAttr crawlPointAttr : list) {
            fixedThreadPool.execute(new CrawlTask(crawlPointAttr));
        }

    }

    //获取采集点属性集合
    private static List<CrawlPointAttr> listCrawlPointAttr(CrawlPointAttr queryAttr) {

        List<CrawlPointAttr> list = new ArrayList<>();
        CrawlPointService CrawlPointService = (CrawlPointService) SpringContext.getContext().getBean("crawlPointService");

        //设置查询条件
        CrawlPoint query = new CrawlPoint();
        query.setId(queryAttr.getId());
        query.setBelong(queryAttr.getBelong());
        query.setCategory(queryAttr.getCategory());

        List<CrawlPoint> crawlPointList = CrawlPointService.list(query);
        for (CrawlPoint crawlPoint : crawlPointList) {

            List<String> linkList = new ArrayList<>();
            if (!StringUtils.isEmpty(crawlPoint.getUrlCrClasspath())) {//采集点是集合
                try {
                    PointLinkCreater pointLinkCreater = (PointLinkCreater) Class.forName(crawlPoint.getUrlCrClasspath())
                            .newInstance();
                    linkList.addAll(pointLinkCreater.get(crawlPoint.getCategory()));
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                    Logger.error("加载类[" + crawlPoint.getUrlCrClasspath() + "]失败", e);
                }
            } else {
                linkList.add(crawlPoint.getUrl());
            }
            //组装采集点属性集合
            for (String link : linkList) {
                CrawlPointAttr crawlPointAttr = new CrawlPointAttr();

                crawlPointAttr.setUrl(link);
                crawlPointAttr.setId(crawlPoint.getId());
                crawlPointAttr.setCategory(crawlPoint.getCategory());
                crawlPointAttr.setUrlCrClassPath(crawlPoint.getUrlCrClasspath());
                crawlPointAttr.setCrawlDetail(crawlPoint.getIsCrawlDetail() == 1 ? true : false);
                crawlPointAttr.setJsonAnalyzePath(crawlPoint.getJsonAnalyzePath());
                crawlPointAttr.setStatus(crawlPoint.getStatus());
                crawlPointAttr.setBelong(crawlPoint.getBelong());
                crawlPointAttr.setMaxPage(crawlPoint.getMaxPage());
                crawlPointAttr.setMethod(crawlPoint.getMethod());
                crawlPointAttr.setCookies(crawlPoint.getCookies());
                crawlPointAttr.setReferer(crawlPoint.getReferer());
                crawlPointAttr.setAccept(crawlPoint.getAccept());
                crawlPointAttr.setResponseEncode(crawlPoint.getResponseEncode());
                crawlPointAttr.setResponseType(crawlPoint.getResponseType());
                crawlPointAttr.setListRecordRule(crawlPoint.getListRecordRule());
                crawlPointAttr.setListAttrRule(crawlPoint.getListAttrRule());
                crawlPointAttr.setLinkRule(crawlPoint.getLinkRule());
                crawlPointAttr.setLinkSelfRule(crawlPoint.getLinkSelfRule());
                crawlPointAttr.setPageIndexClassPath(crawlPoint.getPageIndexClasspath());
                crawlPointAttr.setPageIndexRule(crawlPoint.getPageIndexRule());

                list.add(crawlPointAttr);
            }
        }
        return list;
    }

}
