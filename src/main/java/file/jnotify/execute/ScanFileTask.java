package file.jnotify.execute;

import net.contentobjects.jnotify.JNotify;
import net.contentobjects.jnotify.JNotifyException;
import net.contentobjects.jnotify.JNotifyListener;

/**
 * 扫描文件的任务类
 * 不提供默认构造器 必须提供参数
 * Description  file.jnotify.execute
 * Create by  无语
 * Date on  2019/9/11 9:45
 */
public class ScanFileTask implements  Runnable {

    /**
     * 要扫描的绝对路径
     */
    private String path;

    /**
     * 实际监听文件变化及处理的监听类
     */
    private JNotifyListener jNotifyListener;

    /**
     * 需要监控的文件变化类型
     * JNotify.FILE_CREATED = 1;
     * JNotify.FILE_DELETED = 2;
     * JNotify.FILE_MODIFIED = 4;
     * JNotify.FILE_RENAMED = 8;
     * JNotify.FILE_ANY = 15;
     */
    private int mask;

    /**
     * 监听id
     */
    private int watchId;
    /**
     * 是否级联监视子目录 默认级联
     */
    private boolean watchSubtree=true;

    public ScanFileTask(String path, JNotifyListener jNotifyListener, int mask) {
        this.path = path;
        this.jNotifyListener = jNotifyListener;
        this.mask = mask;
    }
    public ScanFileTask(String path, JNotifyListener jNotifyListener, int mask, boolean watchSubtree) {
        this.path = path;
        this.jNotifyListener = jNotifyListener;
        this.mask = mask;
        this.watchSubtree = watchSubtree;
    }

    @Override
    public void run() {
        try {
            //监听id
            watchId = JNotify.addWatch(path, mask, watchSubtree, jNotifyListener);
        /*    while (true) {
                try {
                    Thread.sleep(1000);
                    System.out.println("守护线程心跳.................");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }*/
        } catch (JNotifyException e) {
            e.printStackTrace();
        }

    }


    /**
     * 移除此对象监听
     */
    public  void removeWatch(){
        try {
            JNotify.removeWatch(watchId);
        } catch (JNotifyException e) {
            e.printStackTrace();
        }

    }




}
