package cn.tedu.test.luban.demo.config;

import cn.tedu.test.luban.demo.bean.Bean01;
import cn.tedu.test.luban.demo.selector.MySelector;
import com.example.config.MyConfiguration02;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"cn.tedu.test.luban.demo.bean"})
//@Import(value={MyConfiguration02.class})
@Import(value={MySelector.class})
public class MyConfiguration01 {
    public MyConfiguration01() {
        System.out.println("当前config01被容器加载了");
    }
    @Bean
    public Bean01 bean01(){
        return new Bean01();
    }
}
