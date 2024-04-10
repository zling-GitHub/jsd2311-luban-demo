package cn.tedu.test.luban.rpc.dubbo;

import com.tarena.demo.luban.cart.api.CartApi;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;

public class ConsumerClient {
    //模拟RPC客户端调用 使用dubbo实现 consumer dubbo配置的步骤 和provider几乎一模一样的
    public static void main(String[] args) {
        CartApi cartApi = get();
        cartApi.deleteUserCart(null);
    }
    public static CartApi get(){
        //1. 准备dubbo启动进程 对象
        DubboBootstrap bootStrap= DubboBootstrap.getInstance();
        bootStrap.application("order-dubbo");
        //2. 提供一个和provider相同的注册中心
        bootStrap.registry(new RegistryConfig("nacos://localhost:8848"));
        //3. 提供给dubbo进程 到注册中心 抓取哪个服务 CartApi
        ReferenceConfig referenceConfig=new ReferenceConfig();
        referenceConfig.setInterface(CartApi.class);
        bootStrap.reference(referenceConfig);
        //4. 上述配置可以成功帮助dubbo获取发现想要的数据 获取一个由底层连接能力的代理对象
        CartApi cartApi = (CartApi) referenceConfig.get();
        System.out.println("当前cartApi实现:"+cartApi.getClass().getName());
        return cartApi;
    }
}
