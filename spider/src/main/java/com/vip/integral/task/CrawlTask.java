package com.vip.integral.task;

import com.alibaba.fastjson.JSONArray;
import com.vip.integral.bean.CrawlPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CrawlTask {

    /*private final static Logger LOGGER = LoggerFactory.getLogger(SpiderTask.class);

    private ListParser listParser;// 列表解析器
    private PageIndexLoader pageIndexLoader;// 分页加载器
    private PageLoader pageLoader;// 页面加载器
    private CrawlPoint crawlPoint;

    public CrawlTask(CrawlPoint crawlPoint) {
        this.crawlPoint = crawlPoint;
    }

    @Override
    public void run() {

        try {
            int pageNum = 0;
            ApplicationContext ctx = SpringContext.getContext();
            taskService = (TaskService) ctx.getBean("taskService");
            spiderResultService = (SpiderResultService) ctx.getBean("spiderResultService");
            AmqpTemplate amqpTemplate = (AmqpTemplate) ctx.getBean("bankCouponSpiderProducerTemplate");
            // 爬虫任务记录初始化
            Task task = new Task();
            task.setTaskId(sourceSpider.getTaskId());
            task.setCrawlPointId(sourceSpider.getCrawlPointId());
            taskService.save(task);
            LOGGER.info("任务ID:{}=====>开始爬取", task.getTaskId());
            // 创建组件
            ComponentBuilder componentBuilder = new ComponentBuilder(sourceSpider);
            listParser = componentBuilder.buildListParser();
            pageIndexLoader = componentBuilder.buildPageIndexLoader();
            pageLoader = componentBuilder.buildPageLoader();

            boolean isNeedSpider = true;// 是否需要爬取
            boolean isFirst = true;
            List<ParseResult> allParseResultList = new ArrayList<>();
            while (pageIndexLoader.isNext() && pageNum < 2) {
                try {
                    String response = pageIndexLoader.next();
                    List<ParseResult> parseResultList = listParser.parse(response, sourceSpider.getResponseType());
                    *//**
                     * 判断该爬取源是否需要爬取 策略：前N条记录DB中是否已存在
                     *//*
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
                        if (sourceSpider.getCrawlDetail()) {
                            try {
                                pageLoader.load(parseResult);
                            } catch (Exception e) {
                                LOGGER.error("爬取优惠信息{}错误:", parseResult.link(), e);
                                // TODO: 16-7-7 异常信息入库
                                continue;
                            }
                        }
                        parseResult.setBank(sourceSpider.getBankCode());
                        parseResult.setCategory(sourceSpider.getCategory());
                        parseResult.setTag(sourceSpider.getTagEn());
                        // 追加数据
                        allParseResultList.add(parseResult);
                        //将结果保存到队列
                        amqpTemplate.convertAndSend(parseResult);
                    }
                    if (!isNeedSpider) {
                        break;
                    }
                    pageNum++;
                } catch (SpiderException e) {
                    LOGGER.error("请求{}失败或解析该列表失败:", pageIndexLoader.httpUriRequest.getURI(), e);
                    //更新任务状态
                    //task.setStatus(e.getCode());
                    //task.setMessage(e.getDescription());
                    //taskService.update(task, task.getTaskId());
                    LOGGER.info("Status:{}, Message:{}", task.getStatus(), task.getMessage());
                    //// TODO: 16-7-7 异常信息入库
                } catch (Exception e) {
                    LOGGER.error("", e);
                }
            }

            String jsonResult = JSONArray.toJSONString(allParseResultList);

            LOGGER.debug("爬取结果：" + jsonResult);

            // 结果入库
            //spiderResultService.save(allParseResultList, task.getTaskId(), task.getCrawlPointId());

            // 更新任务记录状态
            task.setStatus(ExceptionTypeEnum.SUCCESS.code);
            task.setMessage(ExceptionTypeEnum.SUCCESS.description);
            taskService.update(task, task.getTaskId());

            LOGGER.info("Status:{}, Message:{}, Result:{}", task.getStatus(), task.getMessage(), jsonResult);
        } catch (Exception e) {
            LOGGER.error("", e);
        }

    }

    *//**
     * 是否爬取
     *
     * @param parseResult
     * @return
     *//*
    private boolean isSpider(ParseResult parseResult) {
        List<Result> list;
        String title = parseResult.getAttr().get("title");
        String pointLink = parseResult.getPointLink();
        if (StringUtils.isEmpty(title)) {
            list = spiderResultService.get(parseResult.getLink(), pointLink);
        } else {
            list = spiderResultService.get(title, pointLink);
        }
        if (list.size() != 0) {
            spiderResultService.delete(list.get(0));
            return false;
        }
        return true;
    }

    *//**
     * 是否需要爬取
     *//*
    private boolean isNeedSpider(List<ParseResult> parseResultList) {
        // 如果需要采集，记着移除在DB中已存在的N条记录
        for (int i = 0; i < parseResultList.size(); i++) {
            List<Result> list;
            ParseResult parseResult = parseResultList.get(i);
            String title = parseResult.getAttr().get("title");
            String pointLink = parseResult.getPointLink();
            if (title == null) {
                list = spiderResultService.get(parseResult.getLink(), pointLink);
            } else {
                list = spiderResultService.get(title, pointLink);
            }
            if (list.size() != 0) {
                if (i < 2) {
                    continue;
                } else {
                    return false;
                }
            } else {
                return true;
            }
        }
        return false;
    }*/
}
