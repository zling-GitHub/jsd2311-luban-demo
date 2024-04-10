package cn.tedu.test.luban.rpc.dubbo;

import cn.tedu.test.luban.rpc.CartApi;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;

import java.util.ServiceConfigurationError;

/**
 * 作用是启动一个dubbo应用进程
 * 监听一个端口 等待consumer连接调用
 * 提供详细的注册信息 到我们指定的nacos注册中心注册
 * 并且提供可以调用的实现对象CartApiImpl
 */
public class Provider {
    public static void main(String[] args) {
        //1.准备一个可以启动的dubbo进程对象
        DubboBootstrap bootstrap=DubboBootstrap.getInstance();
        //根据当前环境 业务需求 给启动对象 提供各种属性 才能按照要求启动工作
        //2. 到nacos注册 提供注册中心配置属性
        RegistryConfig registryConfig=new RegistryConfig();
        //填写注册中心地址 ${注册中心类型}://${注册中心地址}
        registryConfig.setAddress("nacos://localhost:8848");
        bootstrap.registry(registryConfig);
        //3. 准备底层通信的协议 监听端口
        ProtocolConfig protocolConfig=new ProtocolConfig();
        //不需要tcp udp http
        protocolConfig.setName("dubbo");
        //可以指定具体的端口 20000 需要考虑端口冲突问题 -1 dubbo启动时,会自动从20880 向后寻找可用端口
        protocolConfig.setPort(-1);
        bootstrap.protocol(protocolConfig);
        //4. 提供可以访问的实现CartApiImpl 还要在注册中心告诉别人你访问的这个接口和方法
        ServiceConfig serviceConfig=new ServiceConfig();
        serviceConfig.setInterface(CartApi.class);
        serviceConfig.setRef(new CartApiImpl());
        bootstrap.service(serviceConfig);
        //5. 启动dubbo进程 一直运行 一直监听端口 等待客户端consumer访问 每一个dubbo应用和spring应用一样,都需要一个名字
        bootstrap.application("cart-dubbo");
        bootstrap.start().await();
    }
}
