import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyPaste {
    public static void main(String[] args) throws IOException {
        long s = System.currentTimeMillis();   //计时起点

        /*File file = new File("D:\\FileRecv");  //新建文件夹FileRecv
        file.mkdir();*/

        File in = new File("D:\\Minecraft Sever");
        //FileInputStream fis = new FileInputStream(in);  //创建输入输出流
        //File out = new File("D:\\FileRecv\\Z皇永远滴神.mp4");
        //FileOutputStream fos = new FileOutputStream(out);  //创建输出输出流

        //byte[] arr = new byte[1024 * 1024];   //创建复制用数组

        /*int len = 0;
        while ((len = fis.read(arr)) != -1) {
            fos.write(arr,0,len);
//            System.out.println("用了多少:" + len);
        }*/
        //System.out.println(in.listFiles());

        bianli(in);

        //fos.close();
        //fis.close();

        long e = System.currentTimeMillis();
        System.out.println("复制共用时：" + (e - s));
    }
    public static void bianli (File in) {
        File[] files01 = in.listFiles();
        for (File f : files01) {
            if (f.isDirectory()) {
                bianli(f);
            }
            System.out.println(f);
        }
    }
}
