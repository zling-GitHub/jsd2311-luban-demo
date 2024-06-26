package com.tarena.luban.demo.gateway.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

//@Component
@Slf4j
public class MyFilter02 implements GlobalFilter, Ordered {

    // 网关过滤器的 核心过滤方法, 如果这个过滤器生效, 这个filter方法一定执行

    /**
     * 过滤器的核心逻辑
     * @param exchange 交换机 (请求和响应)
     * @param chain  过滤器链对象 (包含多个过滤器)
     * @return  Mono对象 (异步处理)
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("MyFilter02 filter start");
        // 这里可以做一些自定义的操作, 比如请求参数的修改, 请求头的修改, 响应体的修改等
        // 这里可以返回一个Mono对象, 也可以返回null, 但是不能返回void
        // 如果返回null, 则继续执行下一个过滤器, 如果返回Mono对象, 则会等待这个Mono对象执行完毕后, 才会执行下一个过滤器
        // 这里可以根据自己的需求, 做一些自定义的操作, 比如请求参数的修改, 请求头的修改, 响应体的修改等
        // 读取请求中的携带数据, 需要先拿到请求对象, 在exchange.getRequest().getQueryParams()中获取请求参数
        ServerHttpRequest request = exchange.getRequest();
        RequestPath path = request.getPath();
        URI uri = request.getURI();
        log.info("MyFilter02 filter path: {}", path);
        log.info("MyFilter02 filter URI: {}", uri);
        return chain.filter(exchange);
    }

    /**
     * 过滤器的执行顺序
     * @return 和各个过滤器执行顺序 return 数字越小, 越靠前执行
     * 这里可以根据自己的需求, 设置过滤器的执行顺序, 数字越小, 越靠前执行
     * -2147483648 到 2147483647
     */
    @Override
    public int getOrder() {
        return -100;
    }
}
