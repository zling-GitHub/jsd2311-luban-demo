package com.sentinel.demo03.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class HelloService {

    @SentinelResource(value = "sayHi-service", blockHandler = "sayHiDegrade")
    public String sayHi(String name) {
        try {
            Thread.sleep(110);
            return "Hello, " + name + "!i am sentinel!";
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String sayHiDegrade(String name, BlockException e) {
        return "熔断处理了当前业务方法sayHi!"+new Date();
    }
}
