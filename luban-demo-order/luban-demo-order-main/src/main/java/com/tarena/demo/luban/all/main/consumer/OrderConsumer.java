package com.tarena.demo.luban.all.main.consumer;

import com.tarena.demo.luban.all.main.service.OrderService;
import com.tarena.demo.luban.commons.exception.BusinessDemoException;
import com.tarena.demo.luban.protocol.order.param.OrderAddParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 整合springboot 消费者代码 三部曲
 */
@Component
@Slf4j
@RocketMQMessageListener(topic = "order-topic", consumerGroup = "order-add-consumer")
public class OrderConsumer implements RocketMQListener<OrderAddParam> {

    @Autowired
    private OrderService orderService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void onMessage(OrderAddParam message) {
        ValueOperations opsForValue = stringRedisTemplate.opsForValue();
        // 抢锁
        String keyLock = "luban:demo:order:add:" + message.getOrderSn() + ".lock";
        String rand = new Random().nextInt(9999) + "";
        try {
            Boolean tryLock = opsForValue.setIfAbsent(keyLock, rand, 10, TimeUnit.SECONDS);
            //平判断tryLock=true就是抢锁成功, 否则就是抢锁失败
            while (!tryLock) {
                Thread.sleep(5000);
                // 处理业务逻辑
                tryLock = opsForValue.setIfAbsent(keyLock, rand, 10, TimeUnit.SECONDS);
            }
            orderService.addOrder(message);
        } catch (BusinessDemoException e) {
            log.error("新增订单失败", e);
        } catch (InterruptedException e) {
            log.error("订单新增失败", e);
        } catch (Throwable e) {
            log.error("订单新增失败", e);
            throw e;
        } finally {
            // 释放锁
            String valueLock = (String) opsForValue.get(keyLock);
            if (valueLock != null && valueLock.equals(rand)) {
                stringRedisTemplate.delete(keyLock);
            }
        }
    }
}