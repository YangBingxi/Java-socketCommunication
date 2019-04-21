package client_and_server;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

//服务器类
public class server {

    public static void main(String[] args) throws Exception {

        // 实例化一个list,用于保存所有的User
        List<user> list = new ArrayList<user>();
        // 创建绑定到特定端口的服务器套接字
        @SuppressWarnings("resource")
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("服务端正在开始~");
        // 循环监听客户端连接
        while (true) {
            Socket socket = serverSocket.accept();
            // 每接受一个线程，就随机生成一个一个新用户
            user user = new user("user" + Math.round(Math.random() * 100),socket);

            System.out.println(user.getName() + "正在登录。。。");
            list.add(user);
            // 创建一个新的线程，接收信息并转发
            serverThread thread = new serverThread(user, list);
            thread.start();
        }
    }
}