package com.vip.taskbreak.rule;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.vip.dbservice.model.CrawlPoint;
import com.vip.dbservice.service.BreakRuleService;
import com.vip.dbservice.service.CrawlPointService;
import com.vip.taskbreak.entity.BreakRule;
import com.vip.taskbreak.util.SpringContext;
import com.vip.taskbreak.entity.SourceRoot;
import com.vip.taskbreak.entity.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

/**
 * @author: Zhou Xuanang
 * @Date: 10:53 2016/11/1.
 */
public class RulesLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(RulesLoader.class);

    public static void load() {

        LOGGER.info("开始加载规则...");
        ApplicationContext ctx = SpringContext.getContext();

        BreakRuleService breakRuleService = (BreakRuleService) ctx.getBean("breakRuleService");
        CrawlPointService crawlPointService = (CrawlPointService) ctx.getBean("crawlPointService");

        // 加载规则
        CrawlPoint query = new CrawlPoint();
        List<CrawlPoint> crawlPointList = crawlPointService.list(query);
        List<DataSource> dataSourceList = new ArrayList<>();

        for (CrawlPoint crawlPoint : crawlPointList) {
            /***** 数据源 *****/
            SourceRoot sourceRoot = new SourceRoot();

            sourceRoot.setCrawlPointId(crawlPoint.getId());
            sourceRoot.setUrl(crawlPoint.getUrl());

            /***** 列表页条数规则 *****/
            String countRuleJson;
            try {
                countRuleJson = crawlPoint.getRecordCountRule();
            } catch (NullPointerException e) {
                countRuleJson = "";
            }

            /***** 页数规则 *****/
            String pageIndexRule;
            try {
                pageIndexRule = crawlPoint.getPageIndexRule();
            } catch (NullPointerException e) {
                pageIndexRule = "";
            }

            /***** 分解规则 *****/
            List<com.vip.dbservice.model.BreakRule> modelBreakRuleList = breakRuleService
                    .getByCrawlId(crawlPoint.getId());

            List<BreakRule> breakRuleList = new ArrayList<>();

            for (com.vip.dbservice.model.BreakRule breakRule : modelBreakRuleList) {
                breakRuleList.add(JSON.toJavaObject(JSONObject.parseObject(breakRule.getBreakRule()), BreakRule.class));
            }
            DataSource dataSource = new DataSource(sourceRoot.getCrawlPointId(), sourceRoot, breakRuleList, countRuleJson,
                    pageIndexRule);
            dataSourceList.add(dataSource);
        }

        // 保存到内存
        Rules.init(dataSourceList);
        LOGGER.info("规则加载完毕...");
    }


}
