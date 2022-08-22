package Buffered;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedReaderPractise {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("D:\\FileRecv\\Information\\information.txt"));

//        String line = br.readLine();
//        System.out.println(br.read());

        int len;
        while ((len = br.read()) != -1) {
            System.out.print((char) len);
        }

        String line = br.readLine();
        System.out.println(line);
        br.close();
    }
}
