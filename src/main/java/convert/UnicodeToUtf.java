package convert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description  convert
 * Create by  无语
 * Date on   2019/8/26 10:03
 */
public class UnicodeToUtf {


    /**
     * 文件输出
     * @param outPath
     * @param inPath
     */
        public static void outputFile(Path outPath, Path inPath){

            try {
                Files.newBufferedReader(inPath).lines().forEach(a->{

                    System.out.println(unicodeToString(a));

                });
            } catch (IOException e) {
                e.printStackTrace();
            }


        }


    /**
     * unicode转中文
     * @param str
     * @return
     * @author yutao
     * @date 2017年1月24日上午10:33:25
     */
    public static String unicodeToString(String str) {

        Pattern compile = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");

        Matcher matcher = compile.matcher(str);

        char ch;

        while (matcher.find()) {

            ch = (char) Integer.parseInt(matcher.group(2), 16);

            str = str.replace(matcher.group(1), ch+"" );

        }

        return str;

    }



    public static  void main(String[] args) {
        outputFile(null,Paths.get("C:\\idea_workspace\\slsint_gd\\src\\ApplicationResources_zh_CN.properties"));



    }

}
