package com.operational.platform.analyze;

import com.alibaba.fastjson.JSONObject;
import com.operational.platform.analyze.component.smt.InvestorParser;
import com.operational.platform.analyze.component.smt.OrgParser;
import com.operational.platform.common.bean.MQCrawlJob;
import com.operational.platform.dbservice.model.CrawlJob;
import com.operational.platform.dbservice.service.CrawlJobService;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("mqListener")
public class MqListener implements ChannelAwareMessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(MqListener.class);

    @Autowired
    private InvestorParser investorParser;
    @Autowired
    private OrgParser orgParser;
    @Autowired
    private CrawlJobService crawlJobService;

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {

        String receiveMsg = null;
        try {
            receiveMsg = new String(message.getBody(), "utf-8");
            MQCrawlJob crawlJob = JSONObject.parseObject(receiveMsg, MQCrawlJob.class);
            LOGGER.debug("收到JOB，pageIndex={}", crawlJob.getPageIndex());

            //investorParser.exe(crawlJob);
            orgParser.exe(crawlJob);

            CrawlJob crawlJobDb = new CrawlJob();
            crawlJobDb.setPageIndex(crawlJob.getPageIndex());
            crawlJobDb.setTaskid(crawlJob.getTaskid());
            crawlJobDb.setIsListPageEmpty(crawlJob.isListPageEmpty());
            crawlJobDb.setPointid(crawlJob.getPointid());
            crawlJobDb.setListPage(crawlJob.getListPage());

            crawlJobService.save(crawlJobDb);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

        } catch (Exception e) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            LOGGER.error("error:", e);
        }
    }
}
