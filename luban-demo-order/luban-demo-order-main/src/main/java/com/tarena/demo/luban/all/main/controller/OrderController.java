package com.tarena.demo.luban.all.main.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.tarena.demo.luban.all.main.handler.MyBlockHandler;
import com.tarena.demo.luban.all.main.handler.MyFallbackHandler;
import com.tarena.demo.luban.all.main.service.OrderService;
import com.tarena.demo.luban.commons.restful.JsonResult;
import com.tarena.demo.luban.protocol.order.param.OrderAddParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/base/order")
@Api(tags = "订单")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/add")
    @SentinelResource(value = "addOrder", blockHandler = "addOrderBlock", blockHandlerClass = MyBlockHandler.class, fallback = "addOrderError", fallbackClass = MyFallbackHandler.class)
    @ApiOperation("新增订单的功能")
    public JsonResult addOrder(OrderAddParam orderAddParam) {
        orderService.addOrder(orderAddParam);
        return JsonResult.ok("新增订单完成!");
    }

}
