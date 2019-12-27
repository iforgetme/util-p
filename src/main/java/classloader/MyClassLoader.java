package classloader;

import java.io.*;
import java.net.URLClassLoader;

/**
 * 自定义类加载器 默认父类加载器为系统类加载器  AppClassLoader
 * 初始化时需要传入加载类所在目录
 * Description  classloader
 * Create by  无语
 * Date on  2019/9/10 10:52
 */
public class MyClassLoader extends URLClassLoader {

    public MyClassLoader(String urlDir) {
        super(null);//不委托为父类加载器加载
        this.urlDir=urlDir;
    }

    private  String urlDir="";





    //重写findClass方法


    /**
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {


        byte[] b = loadClassData(name);

        Class<?> aClass = defineClass(name, b, 0, b.length);
        if (aClass == null) {
            throw new ClassNotFoundException(name);
        }
        return aClass;
    }


    /**
     * 将文件加载成byte数组
     * @param name
     * @return
     */
    private byte []  loadClassData(String name){
        byte [] res=null;
        if(name.contains("."))
            name=name.replace(".","//");

        try {
            FileInputStream fileInputStream=new FileInputStream(new File(urlDir+name+".class"));
            ByteArrayOutputStream   byteArrayOutputStream=new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            int b=0;
            while ((b=fileInputStream.read(bytes))!=-1){
                byteArrayOutputStream.write(bytes,0,bytes.length);
            }
            res=byteArrayOutputStream.toByteArray();
            fileInputStream.close();
            byteArrayOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

}
