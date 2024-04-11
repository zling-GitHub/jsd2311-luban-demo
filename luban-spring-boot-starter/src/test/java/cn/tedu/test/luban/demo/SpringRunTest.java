package cn.tedu.test.luban.demo;

import cn.tedu.test.luban.demo.config.MyConfiguration01;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringRunTest {
    //演示spring底层api加载配置类的代码功能.
    @Test
    public void loadConfiguration(){
        AnnotationConfigApplicationContext context=
                new AnnotationConfigApplicationContext(MyConfiguration01.class);
    }
}
