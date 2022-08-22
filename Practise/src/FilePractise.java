import java.io.File;
import java.io.IOException;

public class FilePractise {
    public static void main(String[] args) throws IOException {
        /*File file = new File("D:\\Saves\\111\\222\\333\\444\\555\\666");
        boolean mkdirs = file.mkdirs();
        System.out.println(mkdirs);*/

        File file2 = new File("D:\\Saves");
        boolean delete = file2.delete();
        System.out.println(delete);
    }
}
