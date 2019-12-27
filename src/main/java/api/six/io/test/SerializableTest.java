package api.six.io.test;

import api.six.io.po.Person;
import org.junit.Test;

import java.io.*;

/**
 * Description  api.six.io.test
 * Create by  无语
 * Date on  2019/10/11 14:03
 */
public class SerializableTest {

    @Test
    public void test() throws IOException {
        Person person = new Person("wuYu",27,"man");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("E:\\test\\File\\wuYu"));
        objectOutputStream.writeObject(person);
        objectOutputStream.close();
    }




    @Test
    public void test1() throws IOException, ClassNotFoundException {

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("E:\\test\\File\\wuYu"));
        Object o = objectInputStream.readObject();
        System.out.println(o.toString());
        objectInputStream.close();

    }


}
