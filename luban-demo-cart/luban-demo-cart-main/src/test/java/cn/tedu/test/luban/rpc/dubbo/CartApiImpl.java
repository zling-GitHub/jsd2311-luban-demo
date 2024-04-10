package cn.tedu.test.luban.rpc.dubbo;

import cn.tedu.test.luban.rpc.CartApi;
import com.tarena.demo.luban.protocol.cart.param.CartDeleteParam;

public class CartApiImpl implements CartApi {
    @Override
    public void deleteUserCart(CartDeleteParam cartDeleteParam) {
        System.out.println("你成功的调用了删除购物车:{}"+cartDeleteParam);
    }
}
