package com.sentinel.demo01;

import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SentinelDemo01Main {
    public static void main(String[] args) {
        SpringApplication.run(SentinelDemo01Main.class, args);
        // 设置一个FlowRule，限制每秒钟最多10个请求，超过10个请求则触发熔断
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("sayHi");
        //设置限流级别
        rule.setGrade(1);
        // 设置上限阈值
        rule.setCount(10);
        // 设置每秒钟的请求数
        rules.add(rule);
        // 加载规则
        FlowRuleManager.loadRules(rules);
    }
}
