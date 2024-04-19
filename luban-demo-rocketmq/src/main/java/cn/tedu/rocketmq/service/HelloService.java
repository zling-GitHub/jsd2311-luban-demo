package cn.tedu.rocketmq.service;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class HelloService {

    public String sendMsg(String name) {
        String result = "Hello";
        return result + " " + name + ", " + new Date();
    }
}
