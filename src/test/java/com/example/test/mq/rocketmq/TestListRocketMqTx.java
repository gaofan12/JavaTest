package com.example.test.mq.rocketmq;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author gaofan
 * @version 1.0
 * @date 2022/5/27 16:24
 **/
@SpringBootTest
public class TestListRocketMqTx {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Test
    public void sendMessage() throws InterruptedException {
        sendMessageInTransaction("java1234-rocketmq", "单元测试");
    }

    // 事物消息发送
    public void sendMessageInTransaction(String topic, String msg) throws InterruptedException{
        String[] tags = new String[]{"TagA", "TagB", "TagC", "TagD", "TagE"};
        for(int i=0; i<10; i++){
            Message<String> message = MessageBuilder.withPayload(msg).build();
            String destination = topic + ":" + tags[i % tags.length];
            SendResult sendResult = rocketMQTemplate.sendMessageInTransaction(destination, message, destination);
            System.out.printf("1111%s%n", sendResult);

            //Thread.sleep(10);
        }
    }

}
