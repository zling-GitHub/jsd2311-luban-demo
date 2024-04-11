package cn.tedu.test.luban.demo.bean;

import org.springframework.stereotype.Component;

@Component
public class Bean02 {
    public Bean02() {
        System.out.println("当前bean02被容器加载了");
    }
}