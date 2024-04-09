package cn.tedu.test.luban.rpc;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 代理类
 */
public class HelloServiceClientImpl implements HelloService{
    @Override
    public String sayHi(String name) {
        String result =null;
        try{
            //访问20000端口 发送一个socket进行一次通信
            System.out.println("start...");
            Socket socket=new Socket("localhost",20000);
            //String className=HelloService.class.getName();
            //String methodName="sayHi";
            //使用java序列化将数据压缩成二进制
            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dos=new DataOutputStream(outputStream);
            dos.writeUTF(name);
            DataInputStream dis=new DataInputStream(socket.getInputStream());
            result = dis.readUTF();
            System.out.println("收到响应:"+result);
            System.out.println("finish...");
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String sayBye(String name) {
        return null;
    }
}
