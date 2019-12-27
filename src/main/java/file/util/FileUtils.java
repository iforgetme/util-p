package file.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件工具类
 * Description  file
 * Create by  无语
 * Date on  2019/9/10 10:35
 */
public class FileUtils
{


    //广东统计
    static String classSourceDir="C:\\idea_workspace\\slsint_gd\\out\\artifacts\\slsint_war_exploded\\WEB-INF\\classes\\";
    static  String javaSourceDir="C:\\idea_workspace\\slsint_gd\\src\\";
    static String webSourceDir="C:\\idea_workspace\\slsint_gd\\out\\artifacts\\slsint_war_exploded\\";

 /*   static   String classSourceDir="C:\\idea_workspace\\pbass\\out\\artifacts\\pbass\\WEB-INF\\classes\\";
    static  String webSourceDir="C:\\idea_workspace\\pbass\\out\\artifacts\\pbass\\";
    static String javaSourceDir="";*/







    /**
     * 迭代目录
     * @param filePath
     * @return 返回目录下所有文件
     */
    public static List<Path> loopDir(Path filePath) throws IOException {
        List<Path> resList= new ArrayList<>();
        DirectoryStream<Path> pathStream = Files.newDirectoryStream(filePath);
        for (Path path : pathStream) {
            if(Files.isDirectory(path)){
                List<Path> paths = loopDir(path);
                resList.addAll(paths);
            }else{
                resList.add(path);
            }
        }
        pathStream.close();
        return  resList;
    }

    /**
     * 获取文件 及层级关系
     * @param pathArr  文件路径数组
     *  @param type  0 java 1 class 2 web
     * @return
     */
    public static void mvFileByArrayWithDir(String[] pathArr, Path targetDir,String type) throws IOException {

        for (String s : pathArr) {
            Path path = Paths.get(s);
            String suffixPath="";
            if ("1".equals(type)){

                Path parent = path.getParent();
                List<String> classFile = getClassFile(parent, path);
                //迭代的触底条件
                if (classFile!=null&&classFile.size()>1){
                    mvFileByArrayWithClass(classFile.toArray(new String[classFile.size()]),targetDir);
                    continue;
                }else{
                    suffixPath= path.toString().replace(classSourceDir, "");
                }

            }else if("0".equals(type)){
                suffixPath= path.toString().replace(javaSourceDir, "");
            }else if("2".equals(type)){
                suffixPath= path.toString().replace(webSourceDir, "");
            }

            String separator = File.separator;
            if(suffixPath.startsWith(separator)){
                suffixPath=suffixPath.replaceFirst(separator+separator, "");
            }
            Path target = targetDir.resolve(suffixPath);
            if (Files.notExists(target.getParent() )){
                try {
                    Files.createDirectories(target.getParent());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                if(!Files.isDirectory(path));
                    Files.copy(path,target );
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }






    /**
     * 获取文件 及层级关系
     * @param pathArr  文件路径数组
     * @return
     */
    public static void mvFileByArrayWithClass(String[] pathArr, Path targetDir) throws IOException {
        for (String s : pathArr) {
            Path path = Paths.get(s);
            String suffixPath= path.toString().replace(classSourceDir, "");
            String separator = File.separator;
            if(suffixPath.startsWith(separator)){
                suffixPath=suffixPath.replaceFirst(separator+separator, "");
            }
            Path target = targetDir.resolve(suffixPath);
            if (Files.notExists(target.getParent() )){
                try {
                    Files.createDirectories(target.getParent());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                Files.copy(path,target );
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }




    /**
     * 获取class文件
     * @param parent 父文件夹
     * @param path 当前文件
     * @return
     */
    private static  List<String> getClassFile( Path parent, Path path ){
        String fileName = path.getFileName().toString();
        String preFileName = fileName.substring(0, fileName.indexOf("."));
        List<String> resPathList=new ArrayList<>();
        try {
            DirectoryStream<Path> pathDirectoryStream = Files.newDirectoryStream(parent);

            for (Path inPath : pathDirectoryStream) {
                if (inPath.getFileName().toString().startsWith(preFileName)){
                    resPathList.add(inPath.toString());
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return resPathList;

    }





    /**
     *  基于jdk1.7的WatchService接口实现
     *  基于操作系统的事件触发
     * 可以监测本目录及子目录的事件 在更下级目录只能显示为子目录的modify事件 如果想检测所有子目录时 需要遍历目录  注册事件  会比较慢
     *  modify事件会触发两次
     * @param filePath
     * @throws IOException
     * @throws InterruptedException
     */
    //检查文件变化
    public static void scanFileChange(Path filePath) throws IOException, InterruptedException {
        System.out.println("go to fileChange..............");
      final  WatchService watchService=FileSystems.getDefault().newWatchService();
        filePath.register(watchService, new WatchEvent.Kind[]{StandardWatchEventKinds.ENTRY_MODIFY
                ,StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.OVERFLOW});

        while (true){
            WatchKey take = watchService.take();
            //新增
            take.pollEvents().forEach(pollEvent -> {
                WatchEvent.Kind<?> kind = pollEvent.kind();
                String context = pollEvent.context().toString();
                System.out.println(context);
                if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                    //具体处理类  可以写个接口 根据不同情况实现接口类
                    System.out.println("create .................");
                }
                if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
                    System.out.println("delete .................");
                }
                if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
                    System.out.println("modify .................");
                }
                if (kind == StandardWatchEventKinds.OVERFLOW) {
                    System.out.println("overflow .................");
                }
            });
            boolean reset = take.reset();
            if (!reset)break;//如果重设key失败，退出监听

        }








      /*  Path path = Files.walkFileTree(filePath, new SimpleFileVisitor<Path>() {

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {



                return super.visitFile(file, attrs);
            }


            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                return super.postVisitDirectory(dir, exc);
            }


            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return super.visitFileFailed(file, exc);
            }

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                return super.preVisitDirectory(dir, attrs);
            }

        });*/













    }


    public static void main(String[] args) throws IOException {
        // 当前文件的根路径 c:    工作目录为C:\\eclispe_workspace\\util
     /*   Path path = Paths.get("C:/rafaelnadal/tournaments/2009/dummy/../BNP.txt").normalize();
        System.out.println(path);
        Path path1 = Paths.get("C:/rafaelnadal/tournaments/2009/dummy/../BNP.txt");
        System.out.println(path1);
        Path noNormalize = Paths.get("C:/rafaelnadal/tournaments/./2009/dummy/../BNP.txt");
        Path normalize = Paths.get("C:/rafPaths\n\s+(.)*;$aelnadal/tournaments/./2009/dummy/../BNP.txt").normalize();
        System.out.println(noNormalize);
        System.out.println(normalize);*/
        //Path aml1 = Paths.get("aml");
        //System.out.println(aml.toString());

      //  FileSystem aDefault = FileSystems.getDefault();
      //  System.out.println(aDefault);

   /*     Path path = Paths
                .get("C:");
        Path path1 = path.toAbsolutePath();
        Path src = path1.resolve("src");//追加下级目录
        Path abc = src.resolveSibling("abc");//转换同级目录
        System.out.println(abc);
        System.out.println(src);
        System.out.println(path1);*/

    //TODO 升级文件   后续完善  自动打zip


        String[] javaPaths = getJavaPath(javaSourceDir);
        String[] classPath = getClassPath(classSourceDir);
        String[] webPath = getWebPath(webSourceDir);

        Path classTarget = Paths.get("C:\\Users\\无语\\Desktop\\广东去日志升级文件\\class");
        Path javaTarget = Paths.get("C:\\Users\\无语\\Desktop\\广东升级文件\\java");
        Path webTarget = Paths.get("C:\\Users\\无语\\Desktop\\广东升级文件\\web");

        clearDir(classTarget);
        clearDir(javaTarget);
        clearDir(webTarget);


       // mvFileByArrayWithDir(javaPaths,javaTarget,"0");
       mvFileByArrayWithDir(classPath,classTarget,"1");
   //    mvFileByArrayWithDir(webPath,webTarget,"2");

    }


    private  static void clearDir(Path dir) throws IOException {

        if(Files.isDirectory(dir)){
            List<Path> paths = loopDir(dir);
            for (Path path : paths) {
                Files.delete(path);
            }
             deleteDir(dir);
        }
    }

    /**
     * 内部仅存在空文件夹
     * @param dir
     */
     private static void deleteDir(Path dir) throws IOException {


         Files.walkFileTree(dir, new SimpleFileVisitor<Path>(){
             @Override
             public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                 Files.delete(file);
                 return super.visitFile(file, attrs);
             }

             @Override
             public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                 Files.delete(dir);
                 return super.postVisitDirectory(dir, exc);
             }


         });
     }


    private static String[] getJavaPath(String javaSourceDir) {
        return new String[]{
                 javaSourceDir+"\\com\\krm\\goldformula\\data\\ReportData.java",
                 javaSourceDir+"\\com\\krm\\slsint\\acrossdatacheck\\dao\\hibernate\\AcrossdatacheckDAOHibernate.java",
                 javaSourceDir+"\\com\\krm\\slsint\\acrossdatacheck\\dao\\AcrossdatacheckDAO.java",
                 javaSourceDir+"\\com\\krm\\slsint\\acrossdatacheck\\service\\impl\\AcrossdatacheckServiceImpl.java",
                 javaSourceDir+"\\com\\krm\\slsint\\acrossdatacheck\\service\\AcrossdatacheckService.java",
                 javaSourceDir+"\\com\\krm\\slsint\\acrossdatacheck\\web\\action\\AcrossdatacheckAction.java",
                 javaSourceDir+"\\com\\krm\\slsint\\databuild\\bo\\BuildProgress.java",
                 javaSourceDir+"\\com\\krm\\slsint\\dataMerge\\service\\impl\\MergeDataServiceImpl.java",
                 javaSourceDir+"\\com\\krm\\slsint\\dataMerge\\web\\action\\DataMergeAction.java",
                 javaSourceDir+"\\com\\krm\\slsint\\organmanage\\vo\\Node.java",
                 javaSourceDir+"\\com\\krm\\slsint\\organmanage\\web\\action\\OrganAction.java",
                 javaSourceDir+"\\com\\krm\\slsint\\organmanage\\web\\action\\OrganContrastAction.java",
                 javaSourceDir+"\\com\\krm\\slsint\\organmanage2\\dao\\AreaDAO.java",
                 javaSourceDir+"\\com\\krm\\slsint\\organmanage2\\web\\action\\AreaUserAction.java",
                 javaSourceDir+"\\com\\krm\\slsint\\organmanage2\\web\\action\\TreeAction.java",
                 javaSourceDir+"\\com\\krm\\slsint\\reportdefine\\dao\\ReportDefineDAO.java",
                 javaSourceDir+"\\com\\krm\\slsint\\organmanage2\\vo\\Area.java",
                 javaSourceDir+"\\com\\krm\\slsint\\organmanage2\\web\\action\\AreaAction.java",
                 javaSourceDir+"\\com\\krm\\slsint\\organmanage2\\dao\\impl\\BaseOrganDAO.java",
                 javaSourceDir+"\\com\\krm\\slsint\\organmanage2\\services\\impl\\AreaServiceImpl.java",
                 javaSourceDir+"\\com\\krm\\slsint\\organmanage2\\services\\AreaService.java",
                 javaSourceDir+"\\com\\krm\\slsint\\organmanage2\\dao\\impl\\AreaDAOImpl.java",
                 javaSourceDir+"\\com\\krm\\slsint\\reportdefine\\dao\\hibernate\\ReportDefineDAOHibernate.java",
                 javaSourceDir+"\\com\\krm\\slsint\\reportdefine\\services\\impl\\ReportDefineServiceImpl.java",
                 javaSourceDir+"\\com\\krm\\slsint\\reportdefine\\services\\ReportDefineService.java",
                 javaSourceDir+"\\com\\krm\\slsint\\reportview\\web\\action\\ReportViewAction.java",
                 javaSourceDir+"\\com\\krm\\util\\ConvertUtil.java",
                 javaSourceDir+"\\com\\krm\\slsint\\datacollect\\web\\action\\CollectAction.java",
                 javaSourceDir+"\\com\\krm\\slsint\\organmanage2\\services\\OrganService2.java",
                 javaSourceDir+"\\com\\krm\\slsint\\organmanage2\\services\\impl\\OrganService2Impl.java",
                 javaSourceDir+"\\com\\krm\\slsint\\organmanage2\\dao\\OrganInfoDAO2.java",
                 javaSourceDir+"\\com\\krm\\slsint\\organmanage2\\dao\\impl\\OrganInfoDAO2Impl.java",
                 javaSourceDir+"\\com\\krm\\slsint\\reportdefine\\web\\action\\ReportFormatAction.java"
         };
    }

    private static String[] getClassPath(String classSourceDir) {
        return new String[]{

                classSourceDir + "\\com\\krm\\goldformula\\data\\ReportData.class",
                classSourceDir + "\\com\\krm\\goldreport\\ReportModel.class",
                classSourceDir + "\\com\\krm\\slsint\\reportview\\web\\action\\ReportViewAction.class"
        };
    }



    private static String[] getWebPath(String webSourceDir) {
        return new String[]{
                webSourceDir + "\\datacollect\\collectForm.jsp",
        };
    }





}
