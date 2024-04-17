package com.sentinel.demo02.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String sayHi(String name) {
        return "Hello, " + name + "!i am sentinel!";
    }
}
