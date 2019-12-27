package classloader.init;

import java.io.IOException;
import java.net.URL;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Description  classloader.init
 * Create by  无语
 * Date on  2019/9/10 11:39
 */
public class loadAll {

    /**
     * 存储需要加载的jar包和class文件及文件最后的修改时间
     *
     */
    private static Map<URL,Long> urlMap = new HashMap<>();

    //初始化非classpath下某项目所有class jar文件


    /**
     * 加载目录下的所有jar~class文件至urlMap
     * @param path
     */
    public void loadAllUrl(Path path){
        //首先迭代目录  加载jar包
        try {
            Files.walkFileTree( path,new SimpleFileVisitor<Path>(){
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (file.getFileName().toString().endsWith("jar")||file.getFileName().toString().endsWith("class")){
                        URL url = file.toUri().toURL();
                        FileTime fileTime = attrs.lastModifiedTime();
                        if(urlMap.get(url)==null)
                             urlMap.put(url,fileTime.toMillis());
                        else{
                            Long aLong = urlMap.get(url);
                            if(aLong!=fileTime.toMillis()){
                                urlMap.put(url,fileTime.toMillis());
                            }
                        }
                    }
                    return super.visitFile(file, attrs);
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                            loadAllUrl(dir);
                    return super.postVisitDirectory(dir, exc);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

















}
