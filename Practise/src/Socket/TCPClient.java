package Socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("127.0.0.1",9999);

        OutputStream os = s.getOutputStream();

        os.write("你好，服务器".getBytes());

        InputStream is = s.getInputStream();
        byte[] arr = new byte[1024];
        int len = is.read(arr);
        System.out.println(new String(arr,0,len));

        s.close();
    }
}
