package cn.tedu.test.luban.demo.condition.config;

import cn.tedu.test.luban.demo.condition.OnWindowsCondition;
import cn.tedu.test.luban.demo.condition.anno.ConditionalOnLinux;
import cn.tedu.test.luban.demo.condition.anno.ConditionalOnWindows;
import cn.tedu.test.luban.demo.condition.bean.Bean04;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnLinux
public class MyConditionConfig01 {
    public MyConditionConfig01() {
        System.out.println("当前条件配置config01条件满足 被容器加载");
    }
    @Bean
    @ConditionalOnWindows
    public Bean04 bean04(){
        return new Bean04();
    }
}