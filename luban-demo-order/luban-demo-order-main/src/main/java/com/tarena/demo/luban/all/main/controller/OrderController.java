package com.tarena.demo.luban.all.main.controller;

import com.tarena.demo.luban.all.main.service.OrderService;
import com.tarena.demo.luban.commons.restful.JsonResult;
import com.tarena.demo.luban.protocol.order.param.OrderAddParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/base/order")
@Api(tags = "订单")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @PostMapping("/add")
    @ApiOperation("新增订单的功能")
    public JsonResult addOrder(OrderAddParam orderAddParam){
        orderService.addOrder(orderAddParam);
        return JsonResult.ok("新增订单完成!");
    }

}
