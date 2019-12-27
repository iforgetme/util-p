package api.six.io.filter;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件过滤器  如果本级目录文件路径包含datum则返回true
 *  可以对文件类型做过滤
 * Description  api.six.io.filter
 * Create by  无语
 * Date on  2019/10/11 10:37
 */
public class MyFileFilter implements FileFilter {
    public MyFileFilter(List<String> data ) {
            this.data=data;
    }
    public MyFileFilter(String one ) {
        this.one=one;
        data.add(one);
    }

    public MyFileFilter() {
    }

    private List<String> data=new ArrayList<>();

    private String one="";

    @Override
    public boolean accept(File pathname) {
        for (String datum : data) {
            if (pathname.getName().contains(datum))
                return true;
        }


        return false;
    }
}
