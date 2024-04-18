package com.tarena.demo.luban.all.main.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.tarena.demo.luban.commons.restful.JsonResult;
import com.tarena.demo.luban.protocol.order.param.OrderAddParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyBlockHandler {
    public static JsonResult addOrderBlock(OrderAddParam orderAddParam, BlockException e) {
        log.error("sentinel限制访问了:", e);
        return JsonResult.ok("下单被landlord限制了,请稍后再试!");
    }
}
