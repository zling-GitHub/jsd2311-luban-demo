package com.tarena.demo.luban.all.main.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Value("${server.port}")
    private String port;
    @GetMapping("/hello")
    public String sayHi(String name){
        return "hello "+name+",I am from ["+port+"]";
    }


    @GetMapping("/bye")
    public String sayHi2(String name){
        return "bye "+name+",I am from ["+port+"]";
    }
}
