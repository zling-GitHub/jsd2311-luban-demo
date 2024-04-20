package com.tarena.demo.luban.all.main.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.tarena.demo.luban.all.main.handler.MyBlockHandler;
import com.tarena.demo.luban.all.main.handler.MyFallbackHandler;
import com.tarena.demo.luban.all.main.service.OrderService;
import com.tarena.demo.luban.commons.restful.JsonResult;
import com.tarena.demo.luban.protocol.order.param.OrderAddParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/base/order")
@Api(tags = "订单")
public class OrderController {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @PostMapping("/add")
    @SentinelResource(value = "addOrder", blockHandler = "addOrderBlock", blockHandlerClass = MyBlockHandler.class, fallback = "addOrderError", fallbackClass = MyFallbackHandler.class)
    @ApiOperation("新增订单的功能")
    public JsonResult addOrder(OrderAddParam orderAddParam) {
        Message<OrderAddParam> message = MessageBuilder.withPayload(orderAddParam).build();
        rocketMQTemplate.asyncSend("order-topic", message, new SendCallback() {

            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("发送消息成功:{}", sendResult);
            }

            @Override
            public void onException(Throwable e) {
                log.error("发送消息失败！", e);
            }
        });
        return JsonResult.ok("提交订单完成!");
    }

}
