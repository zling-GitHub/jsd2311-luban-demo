package cn.tedu.test.luban.demo;

import cn.tedu.test.luban.demo.annotation.KeyAnnotation;
import cn.tedu.test.luban.demo.bean.Bean01;
import cn.tedu.test.luban.demo.config.MyConfiguration01;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.support.SpringFactoriesLoader;

import java.util.List;

public class SpringRunTest {
    //演示spring底层api加载配置类的代码功能.
    @Test
    public void loadConfiguration(){
        AnnotationConfigApplicationContext context=
                new AnnotationConfigApplicationContext(MyConfiguration01.class);
        //从上下文 获取容器中bean对象
        Bean01 bean01 = context.getBean(Bean01.class);
        bean01.sayHi("王翠花");
    }
    //读取spring.factories内容
    @Test
    public void loadSpringFactories(){
        //加载/META-INF/spring.factories 读取到里面的key对应value值
        //param1： 要读取的文件中key值的反射
        //param2： 当前程序的类加载其classLoader
        List<String> strings = SpringFactoriesLoader.loadFactoryNames(
                KeyAnnotation.class,
                this.getClass().getClassLoader());
        System.out.println(strings);
    }
}
