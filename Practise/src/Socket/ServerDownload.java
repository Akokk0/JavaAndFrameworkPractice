package Socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerDownload {
    public static void main(String[] args) throws IOException {

        ServerSocket ss = new ServerSocket(9999);

        File file = new File("D:\\FileRecv\\Information\\UPLoad");
        if (!file.exists()) file.mkdir();

        while (true) {
            Socket socket = ss.accept();
            String filename = System.currentTimeMillis() + ".zip";
            FileOutputStream fos = new FileOutputStream("D:\\FileRecv\\Information\\UPLoad\\"+filename);

            new Thread(() -> {
                try {
                    InputStream is = socket.getInputStream();

                    byte[] received = new byte[1024];
                    int receive = 0;
                    while ((receive = is.read(received)) != -1) {
                        fos.write(received);
                    }

                    OutputStream os = socket.getOutputStream();
                    os.write("上传成功".getBytes());
                    socket.shutdownOutput();

                } catch (IOException e) {
                    System.out.println(e);
                } finally {
                    try {
                        socket.close();
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        //ss.close();
    }
}
