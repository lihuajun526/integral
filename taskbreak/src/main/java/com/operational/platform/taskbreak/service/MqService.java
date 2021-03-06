package com.operational.platform.taskbreak.service;

import com.operational.platform.common.bean.MQCrawlJob;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author: Zhou Xuanang
 * @Date: 13:19 16/8/1.
 */
@Service("mqService")
public class MqService {
    @Autowired
    @Qualifier("crawlJobProducerTemplate")
    private AmqpTemplate amqpTemplate;
    @Value("${mq.job.exchange}")
    private String exchangeName;

    public void saveToMq(MQCrawlJob crawlJob) {
        amqpTemplate.convertAndSend(exchangeName, "job.tzr", crawlJob);
    }

    public void saveToMq(Map<String, String> map, String routeKey) {
        amqpTemplate.convertAndSend(exchangeName, routeKey, map);
    }
}
