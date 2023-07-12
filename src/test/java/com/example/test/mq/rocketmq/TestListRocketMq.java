package com.example.test.mq.rocketmq;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.support.MessageBuilder;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author gaofan
 * @version 1.0
 * @date 2022/5/27 15:07
 **/
@SpringBootTest
public class TestListRocketMq {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;


    /**
     * 发送简单消息
     */
    @Test
    public void sendMessage() {
     /*   for (int i = 0; i < 1; i++) {
            rocketMQTemplate.convertAndSend("java1234-rocketmq", "rocketmq大爷，你好！" + i);
        }*/
        rocketMQTemplate.syncSendOrderly("java1234-rocketmq", "同步顺序发送", "1");
    }

    public void sendTest02() {
        //send message synchronously
        rocketMQTemplate.convertAndSend("test-topic-1", "Hello, World!");
        //send spring message
        rocketMQTemplate.send("test-topic-1", MessageBuilder.withPayload("Hello, World! I'm from spring message").build());
        //send messgae asynchronously
        rocketMQTemplate.asyncSend("test-topic-2", new OrderPaidEvent("T_001", new BigDecimal("88.00")), new SendCallback() {
            @Override
            public void onSuccess(SendResult var1) {
                System.out.printf("async onSucess SendResult=%s %n", var1);
            }

            @Override
            public void onException(Throwable var1) {
                System.out.printf("async onException Throwable=%s %n", var1);
            }

        });
        //Send messages orderly
        rocketMQTemplate.syncSendOrderly("orderly_topic",MessageBuilder.withPayload("Hello, World").build(),"hashkey");

        //rocketMQTemplate.destroy(); // notes:  once rocketMQTemplate be destroyed, you can not send any message again with this rocketMQTemplate

    }

    @Data
    @AllArgsConstructor
    public class OrderPaidEvent implements Serializable {
        private String orderId;

        private BigDecimal paidMoney;
    }

}
