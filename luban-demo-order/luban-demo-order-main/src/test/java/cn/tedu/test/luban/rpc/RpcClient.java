package cn.tedu.test.luban.rpc;

import java.io.*;

public class RpcClient {
    public static void main(String[] args) throws IOException {
        HelloService helloService=new HelloServiceProxyImpl();
        String result = helloService.sayHi("王翠花");
        System.out.println(result);
    }
    class RpcObject implements Serializable {
        private String param;
        private String clasName;
        private String method;
    }
}
