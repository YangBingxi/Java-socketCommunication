package client_and_server;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

//��������
public class server {

    public static void main(String[] args) throws Exception {

        // ʵ����һ��list,���ڱ������е�User
        List<user> list = new ArrayList<user>();
        // �����󶨵��ض��˿ڵķ������׽���
        @SuppressWarnings("resource")
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("��������ڿ�ʼ~");
        // ѭ�������ͻ�������
        while (true) {
            Socket socket = serverSocket.accept();
            // ÿ����һ���̣߳����������һ��һ�����û�
            user user = new user("user" + Math.round(Math.random() * 100),socket);

            System.out.println(user.getName() + "���ڵ�¼������");
            list.add(user);
            // ����һ���µ��̣߳�������Ϣ��ת��
            serverThread thread = new serverThread(user, list);
            thread.start();
        }
    }
}