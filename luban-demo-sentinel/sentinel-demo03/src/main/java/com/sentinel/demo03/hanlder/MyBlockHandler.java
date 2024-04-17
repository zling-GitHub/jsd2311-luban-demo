package com.sentinel.demo03.hanlder;

import com.alibaba.csp.sentinel.slots.block.BlockException;

public class MyBlockHandler {

    public static String sayHiError(String name, BlockException e) {
        return "sorry" + name + "被限流了" + e;
    }
}
