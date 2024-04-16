package com.tarena.demo.luban.all.main;

import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan
@MapperScan("com.tarena.demo.luban.all.main.mapper")
@ImportResource("dubbo.xml")
public class OrderMain {
    public static void main(String[] args) {
        SpringApplication application=new SpringApplication(OrderMain.class);
        //springboot中spring容器上下文
        ConfigurableApplicationContext context = application.run(args);
        //main方法启动时就运行了
        //1. 定义一个流控规则类型的数组
        List<FlowRule> flowRules=new ArrayList<>();
        //2. 创建一个流控规则 绑定和限制resource01
        FlowRule flowRule=new FlowRule();
        //3. 填补流控规则对象的3个必要属性
        flowRule.setResource("addOrder");
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
