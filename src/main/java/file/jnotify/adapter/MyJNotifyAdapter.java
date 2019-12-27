package file.jnotify.adapter;

import net.contentobjects.jnotify.JNotifyAdapter;

/**
 * 触发文件变化后的实际处理类
 * Description  file.jnotify
 * Create by  无语
 * Date on  2019/9/11 9:32
 */
public class MyJNotifyAdapter extends JNotifyAdapter {


    @Override
    public void fileCreated(int wd, String rootPath, String name) {

        System.out.println("test  file change-----file created........");
    }

    @Override
    public void fileDeleted(int wd, String rootPath, String name) {
        System.out.println("test  file change-----file Deleted........");
    }

    @Override
    public void fileModified(int wd, String rootPath, String name) {
        System.out.println("test  file change-----file Modified........");
    }

    @Override
    public void fileRenamed(int wd, String rootPath, String oldName, String newName) {
        System.out.println("test  file change-----file Renamed........");
    }


    public static void main(String[] args) {

        //JNotify.addWatch()


    }
}
