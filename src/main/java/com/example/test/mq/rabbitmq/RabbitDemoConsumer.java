package com.example.test.mq.rabbitmq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author gaofan
 * @version 1.0
 * @date 2022/5/24 14:21
 **/
//@Component
public class RabbitDemoConsumer {

    //@RabbitListener(queues = "TestDirectQueue04")
    @RabbitListener(queuesToDeclare = @Queue("test01"))
    public void receiverMessage01(Object msg, Channel channel, Message message) throws IOException {
        System.out.println("收到消息："+msg);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }

    @RabbitListener(queues = RabbitMQConfig.RABBITMQ_DEMO_TOPIC)
    public void receiverMessage02(Object msg, Channel channel, Message message) throws IOException {
        System.out.println("收到消息："+msg);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }
}
