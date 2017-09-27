package com.operational.platform.taskbreak.service;

import com.operational.platform.taskbreak.bean.CrawlJob;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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

    public void saveToMq(CrawlJob crawlJob) {
        amqpTemplate.convertAndSend(exchangeName, "job.route", crawlJob);
    }
}
