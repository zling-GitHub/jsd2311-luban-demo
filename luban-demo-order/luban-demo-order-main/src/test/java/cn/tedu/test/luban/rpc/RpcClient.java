package cn.tedu.test.luban.rpc;

import java.io.*;
import java.net.Socket;

public class RpcClient {
    public static void main(String[] args) throws IOException {
        HelloService helloService=new HelloServiceClientImpl();
        String result = helloService.sayHi("王翠花");
        System.out.println(result);
    }
    class RpcObject implements Serializable {
        private String param;
        private String clasName;
        private String method;
    }
}
