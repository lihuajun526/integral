package com.operational.platform.operate.task;

import com.alibaba.fastjson.JSON;
import com.operational.platform.common.util.Config;
import com.operational.platform.dbservice.model.AttackerAttr;
import com.operational.platform.dbservice.service.AttackerAttrService;
import com.operational.platform.operate.bean.IMessage;
import com.operational.platform.operate.component.attack.impl.tx.AddQQUser;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lihuajun on 16-7-19.
 */
@Component
public class TimerAddQQUser {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimerAddQQUser.class);

    @Autowired
    private AddQQUser addQQUser;
    @Autowired
    private CachingConnectionFactory connectionFactory;
    @Autowired
    private AttackerAttrService attackerAttrService;

    public void execute() {
        Channel channel = null;
        QueueingConsumer consumer = null;
        try {
            channel = connectionFactory.createConnection().createChannel(true);
            HashMap<String,Object> map = new HashMap<>();
            map.put("x-dead-letter-exchange","job.exchange");
            map.put("x-dead-letter-routing-key","qq.user.add.fail");
            channel.queueDeclare(Config.get("queue.qq.user.add"), true, false, false, map);
            consumer = new QueueingConsumer(channel);
            channel.basicConsume(Config.get("queue.qq.user.add"), true, consumer);

            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            IMessage iMessage = JSON.parseObject(message, IMessage.class);
            //iMessage.setAttackerAttr(attackerAttr);

            List<AttackerAttr> list = attackerAttrService.listByBelong("qq");
            for (AttackerAttr attackerAttr : list) {

                //addQQUser.exe(iMessage);
            }
        } catch (Exception e) {
            LOGGER.error("error:", e);
            try {
                channel.basicConsume(Config.get("queue.qq.user.add"), false, consumer);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
