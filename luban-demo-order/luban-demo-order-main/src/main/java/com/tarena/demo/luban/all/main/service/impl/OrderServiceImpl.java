package com.tarena.demo.luban.all.main.service.impl;

import com.tarena.demo.luban.all.main.mapper.OrderMapper;
import com.tarena.demo.luban.all.main.service.OrderService;
import com.tarena.demo.luban.cart.api.CartApi;
import com.tarena.demo.luban.protocol.cart.param.CartDeleteParam;
import com.tarena.demo.luban.protocol.order.dos.OrderDO;
import com.tarena.demo.luban.protocol.order.param.OrderAddParam;
import com.tarena.demo.luban.protocol.stock.param.StockReduceCountParam;
import com.tarena.luban.demo.stock.api.StockApi;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CartApi cartApi;
    @Autowired
    private StockApi stockApi;
    @Override public void addOrder(OrderAddParam param) {
        //减库存
        StockReduceCountParam stockReduceCountParam=new StockReduceCountParam();
        stockReduceCountParam.setReduceCount(param.getCount());
        stockReduceCountParam.setProductCode(param.getProductCode());
        stockReduceCountParam.setOrderSn(param.getOrderSn());
        stockApi.reduceCommodityCount(stockReduceCountParam);
        //stockService.reduceCommodityCount(stockReduceCountParam);
        //增订单
        OrderDO orderDO=new OrderDO();
        BeanUtils.copyProperties(param,orderDO);
        orderMapper.insertOrder(orderDO);
        //删除购物车
        CartDeleteParam cartDeleteParam=new CartDeleteParam();
        cartDeleteParam.setUserId(param.getUserId());
        cartDeleteParam.setProductCode(param.getProductCode());
        //调用删除购物车的接口
        cartApi.deleteUserCart(cartDeleteParam);
        System.out.println("cartApi:"+cartApi.getClass().getName());
    }
}
