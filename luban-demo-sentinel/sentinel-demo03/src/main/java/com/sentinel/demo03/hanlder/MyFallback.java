package com.sentinel.demo03.hanlder;

public class MyFallback {
    public static String sayHiException(String name, Throwable e) {
        return "对不起, " + name + "请求有业务异常" + e;
    }
}
