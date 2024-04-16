package com.sentinel.demo01.controller;

import com.sentinel.demo01.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/hello")
    public String sayHi(String name){
        String result = helloService.sayHi(name);
        return result;
    }


}
