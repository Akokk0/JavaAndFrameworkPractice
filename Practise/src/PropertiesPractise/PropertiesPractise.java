package PropertiesPractise;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class PropertiesPractise {
    public static void main(String[] args) throws IOException {
        File file = new File("D:\\FileRecv\\Information");
        file.mkdir();

        Properties pr = new Properties();
        pr.setProperty("迪丽热巴", "169");
        pr.setProperty("古力娜扎", "168");
        pr.setProperty("马尔扎哈", "167");

//        System.out.println(pr);
        FileWriter fw = new FileWriter("D:\\FileRecv\\Information\\information.txt");

        pr.store(fw,"text comments");

        fw.close();
    }
}
