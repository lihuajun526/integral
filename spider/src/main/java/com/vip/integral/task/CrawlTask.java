package com.vip.integral.task;

import com.alibaba.fastjson.JSONArray;
import com.vip.integral.bean.CrawlPointAttr;
import com.vip.integral.bean.ParseResult;
import com.vip.integral.bean.SpringContext;
import com.vip.integral.component.ComponentBuilder;
import com.vip.integral.component.ListParser;
import com.vip.integral.component.loader.PageIndexLoader;
import com.vip.integral.component.loader.PageLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
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
            ApplicationContext ctx = SpringContext.getContext();
            // 创建组件
            ComponentBuilder componentBuilder = new ComponentBuilder(crawlPointAttr);
            listParser = componentBuilder.buildListParser();
            pageIndexLoader = componentBuilder.buildPageIndexLoader();
            pageLoader = componentBuilder.buildPageLoader();

            boolean isNeedSpider = true;// 是否需要爬取
            boolean isFirst = true;
            List<ParseResult> allParseResultList = new ArrayList<>();
            while (pageIndexLoader.isNext() && pageNum < 2) {
                try {
                    String response = pageIndexLoader.next();
                    List<ParseResult> parseResultList = listParser.parse(response, crawlPointAttr.getResponseType());
                    /**
                     * 判断该爬取源是否需要爬取 策略：前N条记录DB中是否已存在
                     */
                    if (isFirst) {
                        isFirst = false;
                        if (!isNeedSpider(parseResultList)) {
                            break;
                        }
                    }
                    for (ParseResult parseResult : parseResultList) {
                        if (!isSpider(parseResult)) {
                            isNeedSpider = false;
                            parseResultList.subList(parseResultList.indexOf(parseResult), parseResultList.size()).clear();
                            break;
                        }
                        // 加载详细页
                        if (crawlPointAttr.isCrawlDetail()) {
                            pageLoader.load(parseResult);
                        }
                        parseResult.setCategory(crawlPointAttr.getCategory());
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

            String jsonResult = JSONArray.toJSONString(allParseResultList);

            // 结果入库
            //spiderResultService.save(allParseResultList, task.getTaskId(), task.getCrawlPointId());

        } catch (Exception e) {
            LOGGER.error("", e);
        }

    }

    /**
     * 是否爬取
     *
     * @param parseResult
     * @return
     */
    private boolean isSpider(ParseResult parseResult) {
        List<ParseResult> list;

        return true;
    }

    /**
     * 是否需要爬取
     */
    private boolean isNeedSpider(List<ParseResult> parseResultList) {

        return false;
    }

}
