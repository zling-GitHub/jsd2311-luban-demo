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

    }
}
