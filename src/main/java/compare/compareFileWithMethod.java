package compare;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 比较文件方法
 * Description  compare
 * Create by  无语
 * Date on  2019/8/28 15:40
 */
public class compareFileWithMethod {



    /**
     *
     */

    /**
     * @param one
     * @param two
     */
    public static void compareMetheds(String one, String two) throws ClassNotFoundException {


    }

    public static void main(String[] args) throws ClassNotFoundException, MalformedURLException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        Path path = Paths.get("C:\\Users\\无语\\Desktop");

        //web项目下的类加载需要先加载web.xml配置的类？？？ 抛出classNotFoundException异常

        URL url = path.toUri().toURL();

        URL urls[] = new URL[]{url};
        //初始化类加载器
        URLClassLoader loader = new URLClassLoader(urls);
        //通过url类加载器加载class文件
        System.out.println(loader);
        Class<?> aClass = loader.loadClass("test");
        Method[] declaredMethods = aClass.getDeclaredMethods();
        Object o = aClass.newInstance();
        Method method = o.getClass().getMethod("main", String[].class);
        method.invoke(o, (Object) args);
        //  System.out.println(aClass);


    }


}
