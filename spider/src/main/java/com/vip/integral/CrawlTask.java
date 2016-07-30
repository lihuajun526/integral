package com.vip.integral;

import com.alibaba.fastjson.JSON;
import com.vip.integral.bean.CrawlPointAttr;
import com.vip.integral.bean.ParseResult;
import com.vip.integral.bean.SpringContext;
import com.vip.integral.component.ComponentBuilder;
import com.vip.integral.component.ListParser;
import com.vip.integral.component.loader.PageIndexLoader;
import com.vip.integral.component.loader.PageLoader;
import com.vip.integral.model.AttackPage;
import com.vip.integral.service.AttackPageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CrawlTask implements Runnable {

    private final static Logger LOGGER = LoggerFactory.getLogger(CrawlTask.class);
    private ListParser listParser;// 列表解析器
    private PageIndexLoader pageIndexLoader;// 分页加载器
    private PageLoader pageLoader;// 页面加载器
    private CrawlPointAttr crawlPointAttr;

    public CrawlTask(CrawlPointAttr crawlPointAttr) {
        this.crawlPointAttr = crawlPointAttr;
    }

    @Override
    public void run() {

        try {
            int pageNum = 0;
            // 创建组件
            ComponentBuilder componentBuilder = new ComponentBuilder(crawlPointAttr);
            listParser = componentBuilder.buildListParser();
            pageIndexLoader = componentBuilder.buildPageIndexLoader();
            pageLoader = componentBuilder.buildPageLoader();

            boolean isNeedSpider = true;// 是否需要爬取
            boolean isFirst = true;
            List<ParseResult> allParseResultList = new ArrayList<>();
            while (pageIndexLoader.isNext() && pageNum < crawlPointAttr.getMaxPage()) {
                try {
                    String response = pageIndexLoader.next();
                    List<ParseResult> parseResultList = listParser.parse(response);
                    /**
                     * 判断该爬取源是否需要爬取 策略：前N条记录DB中是否已存在
                     */
                    if (isFirst) {
                        isFirst = false;
                        if (!isNeedCrawl(parseResultList)) {
                            break;
                        }
                    }
                    for (ParseResult parseResult : parseResultList) {
                        if (!isCrawl(parseResult)) {
                            isNeedSpider = false;
                            parseResultList.subList(parseResultList.indexOf(parseResult), parseResultList.size()).clear();
                            break;
                        }
                        // 加载详细页
                        if (crawlPointAttr.getCrawlDetail()) {
                            pageLoader.load(parseResult);
                        }
                        parseResult.setCategory(crawlPointAttr.getCategory());
                        parseResult.setBelong(crawlPointAttr.getBelong());
                        parseResult.setPointLink(crawlPointAttr.getUrl());
                        LOGGER.debug(JSON.toJSONString(parseResult));
                        // 追加数据
                        allParseResultList.add(parseResult);
                    }
                    if (!isNeedSpider) {
                        break;
                    }
                    pageNum++;
                } catch (Exception e) {
                    LOGGER.error("", e);
                }
            }
            // 结果入库
            saveAll(allParseResultList);
            LOGGER.info("任务{}完成{},共采集点{}页{}条数据", crawlPointAttr.getTaskid(), crawlPointAttr.getCategory(),
                    pageIndexLoader.getCurCount(), allParseResultList.size());

        } catch (Exception e) {
            LOGGER.error("", e);
        }

    }

    //保存采集结果
    private void saveAll(List<ParseResult> list) {

        AttackPageService attackPageService = (AttackPageService) SpringContext.getContext().getBean("attackPageService");

        for (ParseResult parseResult : list) {
            AttackPage attackPage = new AttackPage();
            attackPage.setBelong(parseResult.getBelong());
            attackPage.setCategory(parseResult.getCategory());
            attackPage.setCount(0);
            attackPage.setLink(parseResult.getLink());
            attackPage.setPointLink(parseResult.getPointLink().replace("{pagenum}", "1"));
            attackPage.setTitle(parseResult.getAttr().get("title"));
            attackPage.setCreateTime(new Date());
            attackPage.setModifyTime(new Date());

            attackPageService.save(attackPage);
        }
    }

    /**
     * 是否爬取
     *
     * @param parseResult
     * @return
     */
    private boolean isCrawl(ParseResult parseResult) {
        List<ParseResult> list;

        return true;
    }

    /**
     * 是否需要爬取
     */
    private boolean isNeedCrawl(List<ParseResult> parseResultList) {

        return true;
    }

}
