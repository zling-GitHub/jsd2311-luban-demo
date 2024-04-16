package com.tarena.luban.demo.gateway.ex;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class MyErrorHandler implements ErrorWebExceptionHandler {
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        String message = "服务器内部错误";
        if (ex instanceof GatewayException) {
            log.error("业务出现异常", ex);
            message = ex.getMessage();
        } else {
            log.error("网关异常", ex);
            message = "系统异常, 请查看日志";
        }
        //拿到响应对象
        ServerHttpResponse response = exchange.getResponse();
        //设置乱码 绝大多数浏览器支持application/json utf-8解码 提前到了UnicodFilter实现
        String finalMessage = message;
        return response.writeWith(Mono.fromSupplier(() -> response.bufferFactory().wrap(finalMessage.getBytes(StandardCharsets.UTF_8))));

    }
}
