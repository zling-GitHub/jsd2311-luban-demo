package com.tarena.demo.luban.all.main.service.impl;

import com.tarena.demo.luban.all.main.mapper.CartMapper;
import com.tarena.demo.luban.all.main.service.CartService;
import com.tarena.demo.luban.protocol.cart.dos.CartDO;
import com.tarena.demo.luban.protocol.cart.param.CartAddParam;
import com.tarena.demo.luban.protocol.cart.param.CartDeleteParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartMapper cartMapper;
    @Override public void cartAdd(CartAddParam param) {
        //DATABASE OBJECT 和表格一一对应
        CartDO cartDO=new CartDO();
        BeanUtils.copyProperties(param,cartDO);
        cartMapper.insertCart(cartDO);
    }
    @Value("${server.port}")
    private String port; //9002 9012 9022
    @Override public void deleteCart(CartDeleteParam param) {
        System.out.println("当前删除购物车的实例是["+port+"]");
        CartDO cartDO=new CartDO();
        BeanUtils.copyProperties(param,cartDO);
        cartMapper.deleteCart(cartDO);
    }
}
