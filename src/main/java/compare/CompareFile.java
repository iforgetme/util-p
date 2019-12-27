package compare;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Description  compare
 *
 * @author 无语
 * Date on  2019/12/25 10:15
 * @since
 */
public class CompareFile {


    public static void main(String[] args) throws IOException {
        Path path = Paths.get("C:\\Users\\无语\\Desktop\\lib");
        Path comPath = Paths.get("C:\\idea_workspace\\slsint_gd\\web\\WEB-INF\\lib");

        DirectoryStream<Path> pathDirectoryStreamCom = Files.newDirectoryStream(comPath);



        DirectoryStream<Path> pathDirectoryStream = Files.newDirectoryStream(path);
        for (Path inPath : pathDirectoryStream) {
            for (Path path1 : pathDirectoryStreamCom) {
                    if (!inPath.getFileName().toString().equals(path1.getFileName().toString())){
                        System.out.println(inPath);
                        break;
                    }
            }


        }
        pathDirectoryStream.close();
        pathDirectoryStreamCom.close();
    }



}
