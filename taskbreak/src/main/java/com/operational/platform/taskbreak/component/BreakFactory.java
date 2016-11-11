package com.operational.platform.taskbreak.component;

import com.operational.platform.dbservice.model.CCrawlPoint;
import com.operational.platform.taskbreak.entity.BreakRule;
import com.operational.platform.taskbreak.entity.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 分解工厂
 * 
 * @author: Zhou Xuanang
 * @Date: 14:35 2016/11/2.
 */
@Service
@Scope("prototype")
public class BreakFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(BreakFactory.class);
    private ExecutorService fixedThreadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

    private DataSource dataSource;
    private String rootUrl;
    private List<BreakRule> breakRuleList;
    private String countRuleJson;
    private String pageIndexRule;

    public void init(DataSource dataSource) {
        this.dataSource = dataSource;
        this.rootUrl = dataSource.getSourceRoot().getUrl();
        this.breakRuleList = dataSource.getBreakRuleList();
        this.countRuleJson = dataSource.getCountRuleJson();
        this.pageIndexRule = dataSource.getPageIndexRule();
    }

    public List<String> process() {
        List<String> urlList = new ArrayList<>();
        List<String> nextLevelUrlList = new ArrayList<>();
        List<String> resultList = new ArrayList<>();

        for (int i = 0; i < breakRuleList.size(); i++) {
            LOGGER.info("分解第{}层...", i + 1);
            if (i == 0) {
                Callable c = new RequestAndParse(rootUrl, countRuleJson, breakRuleList.get(i));
                Future f = fixedThreadPool.submit(c);
                try {
                    try {
                        nextLevelUrlList.addAll((ArrayList<String>) f.get());
                    } catch (ClassCastException e) {
                        resultList.add((String) f.get());
                    }
                } catch (InterruptedException e) {
                    LOGGER.info(e.getMessage());
                } catch (ExecutionException e) {
                    LOGGER.info(e.getMessage());
                }
                urlList.addAll(nextLevelUrlList);

                LOGGER.info("下层URL[{}]个", urlList.size());
            } else {
                nextLevelUrlList.clear();
                List<Future> futureList = new ArrayList<>();
                for (String url : urlList) {
                    Callable c = new RequestAndParse(url, countRuleJson, breakRuleList.get(i));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Future f = fixedThreadPool.submit(c);
                    futureList.add(f);
                }
                try {
                    for (Future future : futureList) {
                        try {
                            if (i == breakRuleList.size() - 1) {

                                resultList.addAll((ArrayList<String>) future.get());
                            } else {
                                nextLevelUrlList.addAll((ArrayList<String>) future.get());
                            }
                        } catch (ClassCastException e) {
                            resultList.add((String) future.get());
                        }
                    }
                } catch (InterruptedException e) {
                    LOGGER.info(e.getMessage());
                } catch (ExecutionException e) {
                    LOGGER.info(e.getMessage());
                }
                urlList.clear();
                if (nextLevelUrlList.size() == 0) {
                    urlList.addAll(resultList);
                } else {
                    urlList.addAll(nextLevelUrlList);
                }
                LOGGER.info("下层URL[{}]个", urlList.size());
            }
        }

        LOGGER.info("采集点个数:" + resultList.size());

        return urlList;

    }

    public List<CCrawlPoint> requestListPage(List<String> urlList) {
        List<CCrawlPoint> crawlList = new ArrayList<>();
        List<Future> futureList = new ArrayList<>();

        for (String url : urlList) {

            Callable c = new ListPageRequestAndParse(url, countRuleJson, pageIndexRule);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Future f = fixedThreadPool.submit(c);
            futureList.add(f);
        }
        try {
            for (Future future : futureList) {
                crawlList.add((CCrawlPoint) future.get());
            }
        } catch (InterruptedException e) {
            LOGGER.info(e.getMessage());
        } catch (ExecutionException e) {
            LOGGER.info(e.getMessage());
        }
        return crawlList;

    }

}
