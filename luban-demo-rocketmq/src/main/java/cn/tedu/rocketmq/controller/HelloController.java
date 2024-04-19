package cn.tedu.rocketmq.controller;

import cn.tedu.rocketmq.service.HelloService;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @GetMapping("/send")
    public String sayHi(String name) {
        // 1.组织封装一个消息对象
        Message<String> msg = MessageBuilder.withPayload(name).build();
        // 2.调用rocltmqTemplate的api方法发送消息
        // 同步消息 同步延迟消息 异步消息 异步延迟消息
//        SendResult result = rocketMQTemplate.syncSend("topic01", msg);
//        logger.info("同步发送消息: {}", result.toString());
        // 同步发送延迟消息 比非延迟消息发送方法多了两个参数, 连接诶timeout超时时间, 延迟级别
//        SendResult result1 = rocketMQTemplate.syncSend("topic01", msg, 500, 5);
//        logger.info("同步发送延迟消息: {}", result1.toString());
        // 异步发送消息
        rocketMQTemplate.asyncSend("topic01", msg, new SendCallback() {

            @Override
            public void onSuccess(SendResult sendResult) {
                logger.info("异步发送消息成功: {}", sendResult.toString());
            }

            @Override
            public void onException(Throwable e) {
                logger.error("异步发送消息失败: {}", e.getMessage());
            }
        });
        // 异步发送延迟消息
        rocketMQTemplate.asyncSend("topic01", msg, new SendCallback() {

            @Override
            public void onSuccess(SendResult sendResult) {
                logger.info("异步发送延迟消息成功: {}", sendResult.toString());
            }

            @Override
            public void onException(Throwable e) {
                logger.error("异步发送延迟消息失败: {}", e.getMessage());
            }
        }, 500, 5);
        return "OK";
    }

}
