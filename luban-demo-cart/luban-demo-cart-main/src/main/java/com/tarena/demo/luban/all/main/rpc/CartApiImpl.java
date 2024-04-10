package com.tarena.demo.luban.all.main.rpc;

import com.tarena.demo.luban.all.main.service.CartService;
import com.tarena.demo.luban.cart.api.CartApi;
import com.tarena.demo.luban.protocol.cart.param.CartDeleteParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * RPC 也是程序的一个入口 和Controller一样
 * RPC 入口 协议 rpc通信 实现dubbo
 * controller 入口 协议 http 实现 springmvc
 * 后面学习消息队列 程序入口还会多一个类型 异步通信
 */
//CartApiImpl spring容器bean id="cartApiImpl"
//NBAApiImpl  spring容器bean id="NBAApiImpl"
@Component
public class CartApiImpl implements CartApi {
    @Autowired
    private CartService cartService;
    @Override
    public void deleteUserCart(CartDeleteParam cartDeleteParam) {
        //删除购物车 调用业务层对象
        cartService.deleteCart(cartDeleteParam);
        System.out.println("删除购物车被调用了:"+cartDeleteParam);
    }
}
