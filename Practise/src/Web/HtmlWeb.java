package Web;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class HtmlWeb {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8080);

        while (true) {
            Socket socket = ss.accept();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        InputStream is = socket.getInputStream();

                        /*byte[] bytes = new byte[1024];
                        int len = 0;
                        while ((len = is.read(bytes)) != -1) {
                            System.out.println(new String(bytes));
                        }*/
                        BufferedReader br = new BufferedReader(new InputStreamReader(is));
                        String unsplit = br.readLine();
                        System.out.println(unsplit);
                        String[] split = unsplit.split(" ");
                        String filepath = split[1].substring(1);

                        FileInputStream fis = new FileInputStream(filepath);
                        OutputStream os = socket.getOutputStream();

                        // 写入HTTP协议响应头,固定写法
                        os.write("HTTP/1.1 200 OK\r\n".getBytes());
                        os.write("Content-Type:text/html\r\n".getBytes());
                        // 必须要写入空行,否则浏览器不解析
                        os.write("\r\n".getBytes());

                        byte[] arr = new byte[1024];
                        int len = 0;
                        while ((len = fis.read(arr)) != -1) {
                            os.write(arr);
                        }

                        fis.close();
                        br.close();
                        socket.close();

                    } catch (IOException e) {
                        System.out.println(e);
                    }
                }
            }).start();
        }
    }
}
