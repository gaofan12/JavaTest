package com.example.test.mq.rabbitmq;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author gaofan
 * @version 1.0
 * @date 2022/5/24 11:56
 **/
@SpringBootTest
public class TestListRabbitMq {


    @Autowired
    private RabbitTemplate rabbitTemplate;  //使用RabbitTemplate,这提供了接收/发送等等方法

    //回调函数: confirm确认
    RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        /**
         * CorrelationData 消息的附加信息，即自定义id
         * ack 代表消息是否被broker（MQ）接收 true 代表接收 false代表拒收。
         * cause 如果拒收cause则说明拒收的原因，帮助我们进行后续处理
         */
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            System.out.println("correlationData-----------" + correlationData);
            System.out.println("ack-----------" + ack);
            if (ack) {
                try {  //更新数据库，可靠性投递机制
                    // db操作略。。。
                    System.err.println("消息已确认....");
                } catch (
                        Exception e) {//如果更新数据库，可靠性投递机制发生错误，就往redis里面存消息ID CorrelationData，后面消费者要判断redis里面是否有该ID,过期时间设置一周
                    //RedisPool.getJedis().set(correlationData.getId(), correlationData.getId(),"NX", "EX",7*24*3600);
                    System.out.println("操作数据库发生错误");
                }
            } else {
                //ack错误打印
                System.err.println(cause);
            }
        }
    };
    //消息失败回调函数
    RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {
        @Override
        public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
            System.err.println("return exchange: " + exchange + ", routingKey: "
                    + routingKey + ", replyCode: " + replyCode + ", replyText: " + replyText);

        }
    };


    @Test
    void sendDirectQueue01() {
        //无需指定交换机，传入空字符串
        //发送消息到队列queue5,这里的queue5其实就是routing key
        //和队列与默认交换机绑定时的binding key相同时消息才能被投递queue5
        rabbitTemplate.convertAndSend("", "TestDirectQueue01", "不喝奶茶的Programmer");
    }

    @Test
    void sendDirectQueue03() {
        //无需指定交换机，传入空字符串
        //发送消息到队列queue5,这里的queue5其实就是routing key
        //和队列与默认交换机绑定时的binding key相同时消息才能被投递queue5
        rabbitTemplate.convertAndSend("", "TestDirectQueue03", "不喝奶茶的Programmer");
    }

    @Test
    void sendDirectQueue02() {
        //无需指定交换机，传入空字符串
        //发送消息到队列queue5,这里的queue5其实就是routing key
        //和队列与默认交换机绑定时的binding key相同时消息才能被投递queue5
        rabbitTemplate.convertAndSend(RabbitMQConfig.RABBITMQ_DEMO_DIRECT_EXCHANGE, RabbitMQConfig.RABBITMQ_DEMO_DIRECT_ROUTING, "不喝奶茶的Programmer2");
    }

}
