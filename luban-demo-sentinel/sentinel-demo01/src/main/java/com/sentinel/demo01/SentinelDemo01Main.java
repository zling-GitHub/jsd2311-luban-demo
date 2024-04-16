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
        //main方法启动时就运行了
        //1. 定义一个流控规则类型的数组
        List<FlowRule> flowRules=new ArrayList<>();
        //2. 创建一个流控规则 绑定和限制resource01
        FlowRule flowRule=new FlowRule();
        //3. 填补流控规则对象的3个必要属性
        flowRule.setResource("sayHi");
        //0=并发限流 1=qps限流
        flowRule.setGrade(1);
        //触发限流规则 限制的阈值
        flowRule.setCount(1);
        //4.添加到流控规则数组对象
        flowRules.add(flowRule);
        //5.最终要注册这个规则对象给sentinel使用
        FlowRuleManager.loadRules(flowRules);
    }
}
