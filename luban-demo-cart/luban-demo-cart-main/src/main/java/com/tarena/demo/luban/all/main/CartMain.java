package com.tarena.demo.luban.all.main;

import com.tarena.demo.luban.all.main.service.CartService;
import com.tarena.demo.luban.protocol.cart.param.CartDeleteParam;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.ObjectInputStream;
import java.net.ServerSocket;

@SpringBootApplication
@MapperScan("com.tarena.demo.luban.all.main.mapper")
public class CartMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CartMain.class, args);
        //context spring上下文 拿到购物车业务对象
        /*CartService service = context.getBean(CartService.class);
        try{
            ServerSocket server=new ServerSocket(20000);
            ObjectInputStream ois=new ObjectInputStream(server.accept().getInputStream())
            CartDeleteParam param=(CartDeleteParam)ois.readObject();
            service.deleteCart(param);
        }catch (Exception e){
        }*/

    }
}
