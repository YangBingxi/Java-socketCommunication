package client_and_server;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/*
 *   �������̵߳�������Ҫ��:
 *   1.�������Կͻ��˵���Ϣ
 *   2.�����յ�����Ϣ��������ת����Ŀ��ͻ���
 * */
public class serverThread extends Thread {

    private user user;
    private List<user> list;

    public serverThread(user user, List<user> list) {
        this.user = user;
        this.list = list;
    }

    public void run() {
        try {
            while (true) {
                // ��Ϣ�ĸ�ʽ��(login||logout||say),������,�շ���,��Ϣ��
                //���ϵض�ȡ�ͻ��˷���������Ϣ
                String msg= user.getBr().readLine();
                System.out.println(msg);
                String[] str = msg.split(",");
                switch (str[0]) {
                    case "logout":
                        remove(user);// �Ƴ��û�
                        break;
                    case "say":
                        sendToClient(str[1], msg); // ת����Ϣ���ض����û�
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("�쳣");
        } finally {
            try {
                user.getBr().close();
                user.getSocket().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendToClient(String username, String msg) {

        for (user user : list) {
            if (user.getName().equals(username)) {
                try {
                    PrintWriter pw =user.getPw();
                    pw.println(msg);
                    pw.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void remove(user user2) {
        list.remove(user2);
    }
}