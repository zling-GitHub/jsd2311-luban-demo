package com.rocket;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 消费消息
 */
public class MyConsumer {
    //消费者进程, 链接rocketmq 监听topic01 获取所有消息
    // 解析body 打印消息字符串
    @Test
    public void consume01() throws Exception {
        //1. 拿到一个可以连接rocketmq的消费者对象 广播 pull 广播 push 集群 pull 集群push
        //集群模式 push消费者
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
        //连接rocketmq地址
        consumer.setNamesrvAddr("localhost:9876");
        //设置消费者分组
        consumer.setConsumerGroup("con-test01");
        //消费者需要连接主题 设置过滤逻辑
        consumer.subscribe("topic01", "*");
        //2. 设置一个消费的监听器Listener 包含核心消费方法consumeMessage
        //消费者对象 只负责连接rocketmq 连接主题 筛选消息,拿到消息 之后逻辑都是监听器
        MessageListenerConcurrently listener =
                new MessageListenerConcurrently() {
                    /**
                     * @param list 消费消息的 消息封装 每一个元素 都是一个消费解析的message对象
                     * 除了批量消费,所有消费拿到的消息 这个对象都只有一个元素
                     * @param consumeConcurrentlyContext 并发消费上下文对象
                     * @return 消费结果 成功CONSUME_SUCCESS 重试RECONSUME_LATER
                     */
                    @Override
                    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                        //1.从入参 list 解析消息 拿到字符串
                        MessageExt messageExt = list.get(0);
                        //2.生产者 怎么把数据 序列化成数组二进制,这里反向解析
                        byte[] body = messageExt.getBody();
                        String message = new String(body, StandardCharsets.UTF_8);
                        System.out.println("消费者01拿到消息:" + message);
                        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                    }
                };
        //监听器交给consumer使用
        consumer.setMessageListener(listener);
//        consumer.setMessageModel(MessageModel.CLUSTERING);
//        consumer.setMessageModel(MessageModel.BROADCASTING);
        //3. 开启连接
        consumer.start();
        //4. 保证线程持续运行,否则无法看到消费逻辑
        while (true) ;
    }

    @Test
    public void consume02() throws Exception {
        //1. 拿到一个可以连接rocketmq的消费者对象 广播 pull 广播 push 集群 pull 集群push
        //集群模式 push消费者
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
        //连接rocketmq地址
        consumer.setNamesrvAddr("localhost:9876");
        //设置消费者分组
        consumer.setConsumerGroup("con-test01");
        //消费者需要连接主题 设置过滤逻辑
        consumer.subscribe("topic02", "*");
        //2. 设置一个消费的监听器Listener 包含核心消费方法consumeMessage
        //消费者对象 只负责连接rocketmq 连接主题 筛选消息,拿到消息 之后逻辑都是监听器
        MessageListenerConcurrently listener =
                new MessageListenerConcurrently() {
                    /**
                     * @param list 消费消息的 消息封装 每一个元素 都是一个消费解析的message对象
                     * 除了批量消费,所有消费拿到的消息 这个对象都只有一个元素
                     * @param consumeConcurrentlyContext 并发消费上下文对象
                     * @return 消费结果 成功CONSUME_SUCCESS 重试RECONSUME_LATER
                     */
                    @Override
                    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                        //1.从入参 list 解析消息 拿到字符串
                        MessageExt messageExt = list.get(0);
                        //2.生产者 怎么把数据 序列化成数组二进制,这里反向解析
                        byte[] body = messageExt.getBody();
                        String message = new String(body, StandardCharsets.UTF_8);
                        System.out.println("消费者01拿到消息:" + message);
                        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                    }
                };
        //监听器交给consumer使用
        consumer.setMessageListener(listener);
//        consumer.setMessageModel(MessageModel.CLUSTERING);
//        consumer.setMessageModel(MessageModel.BROADCASTING);
        //3. 开启连接
        consumer.start();
        //4. 保证线程持续运行,否则无法看到消费逻辑
        while (true) ;
    }

    @Test
    public void consume03() throws Exception {
        //1. 拿到一个可以连接rocketmq的消费者对象 广播 pull 广播 push 集群 pull 集群push
        //集群模式 push消费者
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
        //连接rocketmq地址
        consumer.setNamesrvAddr("localhost:9876");
        //设置消费者分组
        consumer.setConsumerGroup("con-test01");
        //消费者需要连接主题 设置过滤逻辑
        consumer.subscribe("topic02", "上海");
        //2. 设置一个消费的监听器Listener 包含核心消费方法consumeMessage
        //消费者对象 只负责连接rocketmq 连接主题 筛选消息,拿到消息 之后逻辑都是监听器
        MessageListenerConcurrently listener =
                new MessageListenerConcurrently() {
                    /**
                     * @param list 消费消息的 消息封装 每一个元素 都是一个消费解析的message对象
                     * 除了批量消费,所有消费拿到的消息 这个对象都只有一个元素
                     * @param consumeConcurrentlyContext 并发消费上下文对象
                     * @return 消费结果 成功CONSUME_SUCCESS 重试RECONSUME_LATER
                     */
                    @Override
                    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                        //1.从入参 list 解析消息 拿到字符串
                        MessageExt messageExt = list.get(0);
                        //2.生产者 怎么把数据 序列化成数组二进制,这里反向解析
                        byte[] body = messageExt.getBody();
                        String message = new String(body, StandardCharsets.UTF_8);
                        System.out.println("消费者01拿到消息:" + message);
                        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                    }
                };
        //监听器交给consumer使用
        consumer.setMessageListener(listener);
//        consumer.setMessageModel(MessageModel.CLUSTERING);
//        consumer.setMessageModel(MessageModel.BROADCASTING);
        //3. 开启连接
        consumer.start();
        //4. 保证线程持续运行,否则无法看到消费逻辑
        while (true) ;
    }
}
