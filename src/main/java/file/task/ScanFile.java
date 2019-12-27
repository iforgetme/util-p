package file.task;

import file.util.FileUtils;

import java.io.IOException;
import java.nio.file.Path;

/**
 * 扫描任务类
 * Description  file
 * Create by  无语
 * Date on  2019/9/10 15:00
 */
public class ScanFile implements  Runnable{

    private Path   toPath;

    public ScanFile() {
    }

    public ScanFile(Path path ) {
        toPath=path;
    }

    @Override
    public void run() {
        try {
            FileUtils.scanFileChange(toPath);
            System.out.println("is running..........");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
