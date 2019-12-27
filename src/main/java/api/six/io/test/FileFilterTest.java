package api.six.io.test;

import api.six.io.filter.MyFileFilter;
import org.junit.Test;

import java.io.File;

/**
 * Description  api.six.io.test
 * Create by  无语
 * Date on  2019/10/11 11:27
 */
public class FileFilterTest {


       @Test
        public void test(){
            File file = new File("E:\\test\\File\\aaa");

            if (file.isDirectory()){
                File[] as = file.listFiles(new MyFileFilter("aaa"));
                for (File f : as) {
                    System.out.println(f.getName());
                }
            }

        }




}
