package cn.tedu.test.luban.demo.config;

import cn.tedu.test.luban.demo.bean.Bean01;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"cn.tedu.test.luban.demo.bean"})
public class MyConfiguration01 {
    public MyConfiguration01() {
        System.out.println("当前config01被容器加载了");
    }
    @Bean
    public Bean01 bean01(){
        return new Bean01();
    }
}
