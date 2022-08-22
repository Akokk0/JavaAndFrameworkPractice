package Socket;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientUPLoad {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("F:\\BaiduNetdiskDownload\\1.Java基础\\10.JDK8新特性\\24.【Stream流、方法引用.zip");

        Socket s = new Socket("127.0.0.1",9999);
        OutputStream os = s.getOutputStream();

        byte[] sends = new byte[1024];
        int send = 0;
        while ((send = fis.read(sends)) != -1) {
            os.write(sends);
        }
        s.shutdownOutput();

        InputStream is = s.getInputStream();

        byte[] received = new byte[1024];
        int receive = 0;
        while ((receive = is.read(received)) != -1) {
            System.out.println(new String(received,0,receive));
        }

        fis.close();
        s.close();
    }
}
