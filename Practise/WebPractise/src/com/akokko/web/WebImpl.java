package com.akokko.web;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class WebImpl {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8080);

        while (true) {
            Socket socket = ss.accept();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        InputStream is = socket.getInputStream();

                        BufferedReader br = new BufferedReader(new InputStreamReader(is));
                        String[] split = br.readLine().split(" ");
                        String path = split[1].substring(1);
                        FileInputStream fis = new FileInputStream(path);

                        OutputStream os = socket.getOutputStream();

                        // 写入HTTP协议响应头,固定写法
                        os.write("HTTP/1.1 200 OK\r\n".getBytes());
                        os.write("Content-Type:text/html\r\n".getBytes());
                        // 必须要写入空行,否则浏览器不解析
                        os.write("\r\n".getBytes());

                        byte[] bytes = new byte[1024];
                        int len = 0;
                        while ((len = fis.read(bytes)) != -1) {
                            os.write(bytes);
                        }
                    } catch (IOException e) {
                        System.out.println(e);
                    } finally {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }
}
