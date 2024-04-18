package com.tarena.demo.luban.all.main.handler;

import com.tarena.demo.luban.commons.restful.JsonResult;
import com.tarena.demo.luban.protocol.order.param.OrderAddParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyFallbackHandler {

    public static JsonResult addOrderError(OrderAddParam orderAddParam, Throwable e) {
        log.error("新增订单失败:", e);
        return JsonResult.ok("新增订单失败!");
    }
}
