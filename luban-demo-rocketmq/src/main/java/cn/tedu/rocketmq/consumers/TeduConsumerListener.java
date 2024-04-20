package cn.tedu.rocketmq.consumers;

import cn.tedu.rocketmq.entity.TeduUser;
import cn.tedu.rocketmq.service.HelloService;
import cn.tedu.rocketmq.service.TeduService;
import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 根据监听的主题, 获取消息后, 底层Listener调用的对象
 */
@Component
@RocketMQMessageListener(topic = "user-topic01", consumerGroup = "test-consumer-group"
//        selectorExpression = "*",
//        messageModel = MessageModel.BROADCASTING
)
public class TeduConsumerListener implements RocketMQListener<MessageExt> {

    private static final Logger log = LoggerFactory.getLogger(TeduConsumerListener.class);
    @Autowired
    private TeduService teduService;

    /**
     * 核心消费逻辑 结合业务实现的方法
     *
     * @param message 经过底层监听器, 反序列化的body消息数据
     */
   /* public void onMessage(TeduUser message) {
        TeduUser result = null;
        try {
            result = teduService.sendMsg(message);

        } catch (RuntimeException e) {
            // 记录日志, 不会向外抛出
            System.out.println("业务执行失败, 入参, 出参");
        }
        System.out.println("消费者收到消息: " + message + ", 执行业务逻辑, 处理结果: " + result);

    }*/

    @Override
    public void onMessage(MessageExt message) {
        String msgId = message.getMsgId();
        String topic = message.getTopic();
        String tags = message.getTags();
        String keys = message.getKeys();
        String body = new String(message.getBody());
        log.info("收到消息, msgId: {}, topic: {}, tags: {}, keys: {}, body: {}", msgId, topic, tags, keys, body);
        String brokerName = message.getBrokerName();
        int queueId = message.getQueueId();
        log.info("brokerName: {}, queueId: {}", brokerName, queueId);
        TeduUser teduUser = JSON.toJavaObject(JSON.parseObject(body), TeduUser.class);
        log.info("解析消息, teduUser: {}", teduUser);
    }
}
