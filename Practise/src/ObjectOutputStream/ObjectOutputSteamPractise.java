package ObjectOutputStream;

import java.io.*;
import java.util.ArrayList;

public class ObjectOutputSteamPractise {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ArrayList<Person> arr = new ArrayList<Person>();

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:\\FileRecv\\Information\\Object_Person.txt"));

        arr.add(new Person("🐸",18));
        arr.add(new Person("🐒",90));
        arr.add(new Person("🦆",20));

        oos.writeObject(arr);

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:\\FileRecv\\Information\\Object_Person.txt"));

        Object o = ois.readObject();
        System.out.println(o);

        ArrayList<Person> p = (ArrayList<Person>)o;
        System.out.println(p);

        for (Person pe: p) {
            System.out.println(pe);
        }
    }
}
