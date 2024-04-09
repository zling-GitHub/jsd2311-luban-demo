package cn.tedu.test.luban.rpc;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class RpcServer {

    public static void main(String[] args) throws IOException {
        //建立两端通信
        //开启一个socket 服务端监听
        ServerSocket server=new ServerSocket(20000);
        //接收传输过来的socket 阻塞的,没有接收到socket时,线程会阻塞
        while(true){
            System.out.println("...wait connecting...");
            Socket socket = server.accept();
            process(socket);
            System.out.println("...connected...");
        }
    }

    private static void process(Socket socket) throws IOException {
        //接收到对方传递数据 反序列化拿到String字符串
        InputStream inputStream = socket.getInputStream();
        DataInputStream dis=new DataInputStream(inputStream);
        String name = dis.readUTF();
        //对方传递的数据 包含接口名称 包含方法名称 包含参数
        HelloService helloService=new HelloServiceImpl();
        String result = helloService.sayHi(name);
        //服务端将方法功能的返回值 返回给客户端
        DataOutputStream dos=new DataOutputStream(socket.getOutputStream());
        dos.writeUTF(result);
    }
}
