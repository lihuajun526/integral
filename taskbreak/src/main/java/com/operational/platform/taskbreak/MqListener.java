package com.operational.platform.taskbreak;

import com.alibaba.fastjson.JSONObject;
import com.operational.platform.common.bean.MQCrawlJob;
import com.operational.platform.taskbreak.service.MqService;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("mqListener")
public class MqListener implements ChannelAwareMessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(MqListener.class);
    @Autowired
    private MqService mqService;

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {

        String receiveMsg = null;
        try {
            receiveMsg = new String(message.getBody(), "utf-8");

            System.out.println(receiveMsg);

            MQCrawlJob mqCrawlJob = JSONObject.parseObject(receiveMsg, MQCrawlJob.class);

            mqService.saveToMq(mqCrawlJob);

            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            LOGGER.error("error:", e);
        }
    }
}
