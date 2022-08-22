package Buffered;

import java.io.*;
import java.util.HashMap;
import java.util.Set;

public class PaiXu {
    public static void main(String[] args) throws IOException {
        HashMap<String,String> map = new HashMap();

        BufferedReader br = new BufferedReader(new FileReader("D:\\FileRecv\\Information\\in.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\FileRecv\\Information\\out.txt"));

        String len;
        while ((len = br.readLine()) != null) {
            String[] split = len.split("\\.");
            map.put(split[0], split[1]);
        }

        for (String key : map.keySet()) {
            String value = map.get(key);
            bw.write(key + "." + value);
            bw.newLine();
        }

        bw.close();
        br.close();
    }
}
