package cn.tedu.rocketmq.service;

import cn.tedu.rocketmq.entity.TeduUser;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TeduService {

    private static final Logger logger = LoggerFactory.getLogger(TeduService.class);

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public TeduUser sendMsg(TeduUser teduUser) {
        return teduUser;
    }
}