package cn.tedu.test.luban.demo.bean;

public class Bean01 {
    public Bean01() {
        System.out.println("当前bean01被容器加载了");
    }
    public void sayHi(String name){
        System.out.println("bean01对["+name+"]说你好");
    }
}