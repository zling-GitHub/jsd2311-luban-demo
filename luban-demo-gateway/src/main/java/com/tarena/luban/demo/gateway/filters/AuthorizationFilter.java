package com.tarena.luban.demo.gateway.filters;

import com.tarena.luban.demo.gateway.ex.GatewayException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class AuthorizationFilter implements GlobalFilter, Ordered {

    // 网关过滤器的 核心过滤方法, 如果这个过滤器生效, 这个filter方法一定执行

    /**
     * 过滤器的核心逻辑
     *
     * @param exchange 交换机 (请求和响应)
     * @param chain    过滤器链对象 (包含多个过滤器)
     * @return Mono对象 (异步处理)
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("MyFilter01 filter start");
        //参数携带有几个情况 第一种 参数为空 第二种 参数的值为空 第三种 参数有值
        //?age=18 nameParams对象是空的
        //?name&age=18 nameParams对象不是null 但是size=0
        //?name=wch nameParams 不是null size=1 唯一的元素就是wch
        //?token=1234567890 tokenParams 不是null size=1 唯一的元素就是1234567890
        try {
            checkToken(exchange);
        } catch (GatewayException e) {
            log.error("MyFilter01 filter exception", e);
            return Mono.error(e);
        }
        //使用响应对象 可以直接输出数据
        return chain.filter(exchange);
    }

    private void checkToken(ServerWebExchange exchange) throws GatewayException {
        //1. 拿到请求对象
        ServerHttpRequest request = exchange.getRequest();
        MultiValueMap<String, String> queryParams = request.getQueryParams();
        String token = null;
        token = queryParams.getFirst("token");
        if (token == null) {
            token = request.getHeaders().getFirst("Authorization");
            if (token == null) {
                HttpCookie tokens = request.getCookies().getFirst("token");
                if (tokens != null) {
                    token = tokens.getValue();
                }
            }
        }
        if (token == null) {
            throw new GatewayException("token不能为空");
        }
        boolean matches = token.matches("\\d{10}");
        if (!matches) {
            throw new GatewayException("token格式不正确");
        }
    }

   /* private Mono<Void> responseWrite(ServerWebExchange exchange, GatewayException e) {
        //准备返回的数据处理乱码,用响应输出
        String message = e.getMessage();
        //拿到响应对象
        ServerHttpResponse response = exchange.getResponse();
        //设置乱码 绝大多数浏览器支持application/json utf-8解码 提前到了UnicodFilter实现
        return response.writeWith(Mono.fromSupplier(() -> response.bufferFactory().wrap(message.getBytes(StandardCharsets.UTF_8))));
    }*/

    // 和国歌过滤器有关
    @Override
    public int getOrder() {
        return 0;
    }
}
