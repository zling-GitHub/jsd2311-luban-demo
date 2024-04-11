package cn.tedu.test.luban.demo.bean;

import org.springframework.beans.factory.annotation.Value;

public class Bean01 {
    @Value("${luban.user.name}")
    private String username;
    public Bean01() {
        System.out.println("当前bean01被容器加载了");
    }
    public void sayHi(String name){
        System.out.println("bean01对["+name+"]说你好"+"我是:"+username);
    }
}