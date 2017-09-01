package com.operational.platform.spider;

import com.alibaba.fastjson.JSON;
import com.operational.platform.common.util.StrUtil;
import com.operational.platform.spider.bean.ParseResult;
import com.operational.platform.spider.component.ComponentBuilder;
import com.operational.platform.spider.component.loader.PageIndexLoader;
import com.operational.platform.spider.bean.CrawlPointAttr;
import com.operational.platform.spider.bean.SpringContext;
import com.operational.platform.spider.component.ListParser;
import com.operational.platform.spider.component.loader.PageLoader;
import com.operational.platform.dbservice.model.AttackPage;
import com.operational.platform.dbservice.service.AttackPageService;
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
                LOGGER.info("==========================>爬取第{}页数据", ++pageNum);
                try {
                    String response = pageIndexLoader.next();
                    List<ParseResult> parseResultList = listParser.parse(response);

                    if (parseResultList == null || parseResultList.size() == 0) {//如果列表为空则认为没有下一页了，此时退出，如知乎
                        break;
                    }

                    //判断该爬取源是否需要爬取 策略：前N条记录DB中是否已存在
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
                        parseResult.setPointid(crawlPointAttr.getId());
                        parseResult.setPointLink(crawlPointAttr.getUrl().replaceAll("\\{pagenum\\}", String.valueOf(pageNum + 1)));

                        //保存到数据库
                        saveToDb(parseResult);
                        LOGGER.debug(JSON.toJSONString(parseResult));
                        // 追加数据
                        //allParseResultList.add(parseResult);
                    }
                    if (!isNeedSpider) {
                        break;
                    }
                    pageNum++;

                    Thread.sleep(crawlPointAttr.getSleepTime() == null ? 10 : crawlPointAttr.getSleepTime());

                } catch (Exception e) {
                    LOGGER.error("", e);
                }
            }
            LOGGER.debug("任务{}完成{},共采集点{}页{}条数据", crawlPointAttr.getTaskid(), crawlPointAttr.getCategory(),
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
            attackPage.setLink(parseResult.getLink());

            //判断数据库中是否已存在
            if (attackPageService.listByCondition(attackPage).size() > 0)
                continue;

            attackPage.setBelong(parseResult.getBelong());
            attackPage.setCategory(parseResult.getCategory());
            attackPage.setCount(0);
            attackPage.setPointLink(parseResult.getPointLink().replace("{pagenum}", "1"));
            attackPage.setTitle(parseResult.getTitle());
            attackPage.setAttr(JSON.toJSONString(parseResult.getAttr()));
            attackPage.setCreateTime(new Date());
            attackPage.setUpdateTime(new Date());
            LOGGER.info("爬取了一个新用户{}", attackPage.getAttr());
            attackPageService.save(attackPage);
        }
    }

    //保存采集结果
    private void saveToDb(ParseResult parseResult) {

        AttackPageService attackPageService = (AttackPageService) SpringContext.getContext().getBean("attackPageService");

        AttackPage attackPage = new AttackPage();
        attackPage.setLink(parseResult.getLink());
        //判断数据库中是否已存在
        List<AttackPage> list = attackPageService.listByCondition(attackPage);
        attackPage.setAttr(JSON.toJSONString(parseResult.getAttr()));
        attackPage.setMd5(StrUtil.md5(attackPage.getAttr()));
        if (list.size() > 0) {
            AttackPage attackPageDb = list.get(0);
            if (attackPageDb == null || attackPageDb.getMd5() == null) {
                return;
            }
            if (attackPageDb.getMd5().equals(attackPage.getMd5()))
                attackPageDb.setFlag(3);//没有变化
            else {
                attackPageDb.setFlag(2);//更新
                attackPageDb.setAttr(attackPage.getAttr());
                attackPageDb.setMd5(attackPage.getMd5());
                LOGGER.info("有更新[{}]", attackPage.getAttr());
            }
            attackPageService.save(attackPageDb);
            return;
        }
        attackPage.setFlag(1);//新增
        attackPage.setBelong(parseResult.getBelong());
        attackPage.setCategory(parseResult.getCategory());
        attackPage.setCount(0);
        attackPage.setPointid(parseResult.getPointid());
        attackPage.setPointLink(parseResult.getPointLink());
        attackPage.setTitle(parseResult.getTitle());
        attackPage.setCreateTime(new Date());
        attackPage.setUpdateTime(new Date());

        attackPageService.save(attackPage);
        LOGGER.debug("新的视频[{}]", attackPage.getAttr());
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
