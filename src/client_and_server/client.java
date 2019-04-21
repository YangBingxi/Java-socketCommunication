package client_and_server;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/*
 *   client线程主要是负责：
 *   1.发送信息
 *   2.一直接收信息，并解析
 * */
public class client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 9999);
            //开启一个线程接收信息，并解析
            clientThread thread=new clientThread(socket);
            thread.setName("Client1");
            thread.start();
            //主线程用来发送信息
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out=new PrintWriter(socket.getOutputStream());
            while(true)
            {
                String s=br.readLine();
                out.println(s);
                //out.write(s+"\n");
                out.flush();
            }
        }catch(Exception e){
            System.out.println("服务器异常");
        }
    }
}