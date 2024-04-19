package com.rocket;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

/**
 * 生产者 发送消息
 */
public class MyProducer {

    // 发送消息
    @Test
    public void sendMessage() throws Exception {
        //1. 组织一个可以连接rocketmq的生产者对象
        DefaultMQProducer producer = new DefaultMQProducer();
        // rocketmq对生产者有管理的结构 生产对的消息内容是否是同一个业务
        producer.setProducerGroup("test-group-01");
        // 设置rocketmq的连接地址 localhost:9876
        producer.setNamesrvAddr("localhost:9876");
        // 开启连接 如果运行的rocketmq资源比较饱满了, 可能导致start连接过慢
        producer.start();
        // Thread.sleep(1000);
        //2. 开启连接之后, 整理封装一个发送的消息对象
        Message message = new Message();
        message.setTopic("topic02");
        message.setTags("tag01");
        // message包装数据内容
        String msg = "第一条消息";
        byte[] bytes = msg.getBytes(StandardCharsets.UTF_8);
        message.setBody(bytes);
        //3. 发送执行, 获取发送反馈结果
        //TODO springboot整合 同步发送消息 异步发送消息
        SendResult result = producer.send(message);
        //result包括很多信息,比如发送的结果
        SendStatus sendStatus = result.getSendStatus();
        if (sendStatus.name().equals("SEND_OK")) {
            System.out.println("消息发送成功");
        }
        // 如果这种发送代码在程序中编写, 每次发送完毕, 关闭连接
        producer.shutdown();
    }
}