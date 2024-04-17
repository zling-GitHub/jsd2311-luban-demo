package com.sentinel.demo02.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.sentinel.demo02.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/hello")
    public String sayHi(String name) {
        log.info("lubandemo02 sayHi方法收到参数为：{}", name);
        Entry entry = null;
        String result = null;
        try {
            entry = SphU.entry("sayHi");
            result = helloService.sayHi(name);
        } catch (BlockException e) {
            log.error("sentinel限制访问了:", e);
            return "sorry";
        } finally {
            if (entry != null)
                entry.exit();
        }
        log.info("lubandemo02 sayHi方法收到参数为：{}", name);
        return result;
    }


}
