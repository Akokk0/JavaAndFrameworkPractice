package Socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(9999);  //创建服务器对象

        Socket skt = ss.accept();  //获取客户端发来的Socket对象

        InputStream is = skt.getInputStream();  //获取输入流

        byte[] arr = new byte[1024];
        int len = is.read(arr);
        System.out.println(new String(arr,0,len));


        OutputStream os = skt.getOutputStream();

        os.write("收到谢谢".getBytes());

        skt.close();
        ss.close();
    }
}
