package com.example.config;

import com.example.bean.Bean03;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration02 {
    public MyConfiguration02() {
        System.out.println("其它厂商config02被容器加载了");
    }
    @Bean
    public Bean03 bean03(){
        return new Bean03();
    }
}
