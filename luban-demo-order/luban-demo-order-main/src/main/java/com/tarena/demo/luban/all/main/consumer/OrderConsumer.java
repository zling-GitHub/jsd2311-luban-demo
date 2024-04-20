package com.tarena.demo.luban.all.main.consumer;

import com.tarena.demo.luban.all.main.service.OrderService;
import com.tarena.demo.luban.commons.exception.BusinessDemoException;
import com.tarena.demo.luban.protocol.order.param.OrderAddParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 整合springboot 消费者代码 三部曲
 */
@Component
@Slf4j
@RocketMQMessageListener(topic = "order-topic", consumerGroup = "order-add-consumer")
public class OrderConsumer implements RocketMQListener<OrderAddParam> {

    @Autowired
    private OrderService orderService;

    @Override
    public void onMessage(OrderAddParam message) {
        try {
            orderService.addOrder(message);
        } catch (BusinessDemoException e) {
            log.error("生成订单异常", e);
        }
    }
}
