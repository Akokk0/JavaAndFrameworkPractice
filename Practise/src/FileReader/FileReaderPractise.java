package FileReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderPractise {
    public static void main(String[] args) {
        /*FileReader fr = new FileReader("D:\\Minecraft cursors by Exoridus\\Arrow1.cur");
        char[] cr = new char[1024];

        int len = 0;
        while ((len = fr.read(cr)) != -1) {
            System.out.println(new String(cr,0,len));
        }
        fr.close();*/


        byte[] bt = new byte[1024];

        try (FileInputStream fis = new FileInputStream("D:\\Minecraft Sever\\1.7.10\\forge-1.7.10-10.13.4.1558-1.7.10-installer.jar")) {
            int nen = 0;
            while ((nen = fis.read(bt)) != -1) {
                System.out.println(new String(bt).toCharArray());
            }
        }catch (IOException e) {
            System.out.println(e);
        }
    }
}
