package com.tarena.demo.luban.all.main;

import com.tarena.demo.luban.all.main.service.CartService;
import com.tarena.demo.luban.protocol.cart.param.CartDeleteParam;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

import java.io.ObjectInputStream;
import java.net.ServerSocket;

@SpringBootApplication
@MapperScan("com.tarena.demo.luban.all.main.mapper")
//额外导入xml配置资源
@ImportResource("dubbo.xml")
public class CartMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CartMain.class, args);
    }
}
