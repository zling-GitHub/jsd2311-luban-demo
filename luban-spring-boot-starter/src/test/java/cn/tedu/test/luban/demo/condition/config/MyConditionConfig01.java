package cn.tedu.test.luban.demo.condition.config;

import cn.tedu.test.luban.demo.condition.OnWindowsCondition;
import cn.tedu.test.luban.demo.condition.anno.ConditionalOnLinux;
import cn.tedu.test.luban.demo.condition.anno.ConditionalOnWindows;
import cn.tedu.test.luban.demo.condition.bean.Bean04;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ConditionalOnLinux
//@ConditionalOnClass(name={"cn.tedu.test.luban.demo.bean.Bean01"}) //指定一些类 反射参数 包+类字符串名称
//@ConditionalOnMissingClass(value={"cn.tedu.test.luban.demo.bean.Bean01"})
//1. 必须存在一个luban.user.name的属性 存在条件满足 反之不满足
//@ConditionalOnProperty(prefix="luban",value="user.name")
//@ConditionalOnProperty(prefix="luban.user",value="name")
//@ConditionalOnProperty(value="luban.user.name")
//2. 不仅要求key值存在 要求value值是固定的内容 违反一条 则条件不满足
//@ConditionalOnProperty(value="luban.user.name",havingValue = "wangcuihua")
//3. 不仅要有key值 ,value等于指定内容.但是如果没有这个key值 ,条件判断直接满足
@ConditionalOnProperty(
        value="luban.user.name",
        havingValue = "wangcuihua",
        matchIfMissing = true)
public class MyConditionConfig01 {
    public MyConditionConfig01() {
        System.out.println("当前条件配置config01条件满足 被容器加载");
    }
    @Bean
    //@ConditionalOnWindows
    public Bean04 bean04(){
        return new Bean04();
    }
}