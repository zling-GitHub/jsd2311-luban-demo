package com.tarena.demo.luban.all.main.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.tarena.demo.luban.cart.api.CartApi;
import com.tarena.demo.luban.protocol.cart.param.CartDeleteParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RpcCartDeleteService {

    @Autowired
    private CartApi cartApi;

    @SentinelResource(value = "cartDelete", blockHandler = "cartDeleteDegrade")
    public void cartDelete(CartDeleteParam param) {
        cartApi.deleteUserCart(param);
    }

    public void cartDeleteDegrade(CartDeleteParam param, BlockException ex) {
        // todo 添加监控, 监听管理
        System.out.println("购物车远程调用失败, 非核心业务, 无关紧要");
    }
}
