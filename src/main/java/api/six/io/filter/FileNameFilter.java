package api.six.io.filter;

import java.io.File;
import java.io.FilenameFilter;

/**
 * 文件名称过滤器  类似FileFilter
 * Description  api.six.io.filter
 * Create by  无语
 * Date on  2019/10/11 11:36
 */
public class FileNameFilter implements FilenameFilter {

    public FileNameFilter(String fileName) {
        this.fileName = fileName;
    }

    private String fileName;


    @Override
    public boolean accept(File dir, String name) {

        return name.equals(fileName);
    }




}
