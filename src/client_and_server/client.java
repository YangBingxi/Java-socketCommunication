package client_and_server;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/*
 *   client�߳���Ҫ�Ǹ���
 *   1.������Ϣ
 *   2.һֱ������Ϣ��������
 * */
public class client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 9999);
            //����һ���߳̽�����Ϣ��������
            clientThread thread=new clientThread(socket);
            thread.setName("Client1");
            thread.start();
            //���߳�����������Ϣ
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
            System.out.println("�������쳣");
        }
    }
}