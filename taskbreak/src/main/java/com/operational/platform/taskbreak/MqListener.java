package com.operational.platform.taskbreak;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("mqListener")
public class MqListener implements ChannelAwareMessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(MqListener.class);

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {

        String receiveMsg = null;
        try {
            receiveMsg = new String(message.getBody(), "utf-8");
            Map<String, String> attr = JSONObject.parseObject(receiveMsg, HashMap.class);

            TzrTask.exe(attr);

            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            LOGGER.error("error:", e);
        }
    }
}
