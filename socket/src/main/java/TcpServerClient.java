import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TcpServer
 *
 * @author zhucj
 * @since 20210325
 */
public class TcpServerClient {

}

class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(6666);
        while (true) {
            Socket socket = server.accept();
            System.out.println("一个客户连接了");

            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            String str;
            //从客户端读取数据
            if ((str = dis.readUTF()) != null) {
                System.out.println(str);
                System.out.println("from " + socket.getInetAddress() + ", port " + socket.getPort());
            }
            //服务器写到客户端
            dos.writeUTF("Hello, " + socket.getInetAddress() + ", port" + socket.getPort()/**客户端的端口*/);

            dos.flush();
            dis.close();
            dos.close();
            socket.close();
        }
    }

}

class Client {

    public static void main(String[] args) throws IOException {

        //这里会进行 tcp/ip 握手
        Socket client = new Socket("127.0.0.1", 6666);
        OutputStream os = client.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        dos.writeUTF("Hello, Server!");

        DataInputStream dis = new DataInputStream(client.getInputStream());
        System.out.println(dis.readUTF());

        dos.flush();
        dos.close();
        dis.close();

        client.close();
    }
}