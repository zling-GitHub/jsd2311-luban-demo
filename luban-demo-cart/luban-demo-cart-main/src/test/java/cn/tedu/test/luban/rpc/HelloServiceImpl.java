package cn.tedu.test.luban.rpc;

public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHi(String name) {
        return "hello ["+name+"] RPC";
    }

    @Override
    public String sayBye(String name) {
        return null;
    }
}
