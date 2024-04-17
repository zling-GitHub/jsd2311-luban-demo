package com.sentinel.demo03.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.sentinel.demo03.hanlder.MyBlockHandler;
import com.sentinel.demo03.hanlder.MyFallback;
import com.sentinel.demo03.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @SentinelResource(value = "sayHi", blockHandler = "sayHiError", blockHandlerClass = MyBlockHandler.class, fallback = "sayHiException", fallbackClass = MyFallback.class)
    @GetMapping("/hello")
    public String sayHi(String name) {
        String result = null;
        result = helloService.sayHi(name);
        log.info("sayHi: {}", result);
        int a = 1 / 0;
        return result;
    }

    /*public String sayHiException(String name, Throwable e) {
        return "对不起, 数据彻底错乱了";
    }*/

    /*public String sayHiError(String name, BlockException e) {
        return "sorry" + name + "被限流了" + e;
    }*/


}
