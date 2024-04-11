package cn.tedu.test.luban.demo.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration01 {
    public MyConfiguration01() {
        System.out.println("当前config01被容器加载了");
    }
}
