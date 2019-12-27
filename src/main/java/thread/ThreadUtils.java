package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description  thread
 * Create by  无语
 * Date on  2019/9/10 14:25
 */
public class ThreadUtils {
        /**
         *  可缓存的线程池 无界线程池
         */
       private final static ExecutorService cachedService= Executors.newCachedThreadPool();

        /**
         *  固定大小的线程池  有界线程池
         */
       private final static  ExecutorService fixedService=Executors.newFixedThreadPool(5);


        /**
         *  单线程化的无界线程池  顺序执行任务
         */
       private final static ExecutorService singleService=Executors.newSingleThreadExecutor();

        /**
         *  支持定时任务及周期性调用的有界线程池
         */
       private final static   ExecutorService scheduledService=Executors.newScheduledThreadPool(5);


    /**
     * @return  固定大小5的线程池
     */
       public static  ExecutorService getFixedService(){

           return  fixedService;
       }


       public static ExecutorService getCachedService(){

           return  cachedService;
       }

        public static ExecutorService getSingleService(){

            return  singleService;
        }

    /**
     * @return    固定大小5的线程池
     */
        public static ExecutorService getScheduledService(){

            return  scheduledService;
        }


        public static void startThread(Runnable run){
            Thread thread = new Thread(run);
            thread.start();
        }

}
