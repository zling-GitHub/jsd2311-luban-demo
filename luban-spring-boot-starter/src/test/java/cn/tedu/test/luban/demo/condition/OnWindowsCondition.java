package cn.tedu.test.luban.demo.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class OnWindowsCondition implements Condition {
    /**当条件注解 使用当前条件判断时,最终条件是否满足
     * 取决于这个方法的返回值
     * @param context 条件上下文 可以获取容器环境 系统的环境
     * @param metadata 条件注解所在的类的反射数据
     * @return false表示条件不满足 true表示条件满足
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment environment = context.getEnvironment();
        String property = environment.getProperty("os.name");
        System.out.println("当前系统名称："+property);
        if (property!=null && property.contains("Windows")){
            //说明当前系统是windows 满足条件
            return true;
        }
        return false;
    }
}
