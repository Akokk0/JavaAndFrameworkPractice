package Buffered;

import java.io.*;

public class BufferedPractise {
    public static void main(String[] args) throws IOException {
        long s = System.currentTimeMillis();

        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("D:\\FileRecv\\Z皇永远滴神.mp4"));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("D:\\FileRecv\\Information\\Z皇永远滴神.mp4"));

        byte[] bytes = new byte[1048576];
        int len = 0;
        while ((len = bis.read(bytes)) != -1) {
            bos.write(bytes,0,len);
        }

        bos.close();
        bis.close();

        long e = System.currentTimeMillis();
        System.out.println("复制所用的时间：" + (e - s));
    }
}
