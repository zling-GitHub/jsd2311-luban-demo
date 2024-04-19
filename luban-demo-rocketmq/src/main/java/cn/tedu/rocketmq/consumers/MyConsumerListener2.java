package cn.tedu.rocketmq.consumers;

import cn.tedu.rocketmq.service.HelloService;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 根据监听的主题, 获取消息后, 底层Listener调用的对象
 */
@Component
@RocketMQMessageListener(topic = "topic01", consumerGroup = "test-consumer-group"
//        selectorExpression = "*",
//        messageModel = MessageModel.BROADCASTING
)
public class MyConsumerListener2 implements RocketMQListener<String> {

    @Autowired
    private HelloService helloService;

    /**
     * 核心消费逻辑 结合业务实现的方法
     *
     * @param message 经过底层监听器, 反序列化的body消息数据
     */
    @Override
    public void onMessage(String message) {
        String result = null;
        try {
            result = helloService.sendMsg(message);

        } catch (RuntimeException e) {
            // 记录日志, 不会向外抛出
            System.out.println("业务执行失败, 入参, 出参");
        }
        System.out.println("消费者收到消息: " + message + ", 执行业务逻辑, 处理结果: " + result);

    }
}
