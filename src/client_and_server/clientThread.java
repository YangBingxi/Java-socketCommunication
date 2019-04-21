package client_and_server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
/**
 *   ���ã�һֱ���շ����ת����������Ϣ
 * */
public class clientThread extends Thread {

    private Socket socket;
    public clientThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(
                    inputStream);
            BufferedReader br = new BufferedReader(inputStreamReader);
            try {
                // ��Ϣ�ĸ�ʽ��(login||logout||say),������,�շ���,��Ϣ��
                while (true) {
                    String msg=br.readLine();
                    System.out.println(msg);
                    String[] str = msg.split(",");
                    switch (str[0]) {
                        case "say":
                            System.out.println(str[2] + " ��   " + str[1] + " say:  "
                                    + str[3]);
                            break;
                        default:
                            break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
