package org.luban.config;

import org.luban.user.LubanUser;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//条件注解 需要使用LubanUser的 满足条件配置 不需要使用 就不用满足
//必须存在一个luban.user.enable key值 值必须是true
@ConditionalOnProperty(value="luban.user.enable",havingValue = "true")
public class LubanUserAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public LubanUser lubanUser(){
        return new LubanUser();
    }
}
