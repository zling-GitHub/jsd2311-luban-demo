package cn.tedu.test.luban.demo.condition.anno;

import cn.tedu.test.luban.demo.condition.OnLinuxCondition;
import cn.tedu.test.luban.demo.condition.OnWindowsCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * 在windows的操作系统下 条件满足
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Conditional(OnLinuxCondition.class)
public @interface ConditionalOnLinux {
}
