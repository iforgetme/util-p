package mq.provider;

import mq.util.QueueManage;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Description  mq.provider
 * Create by  无语
 * Date on  2019/9/17 14:29
 */
public class ProviderRunnable implements  Runnable {

    public static int taskId=0;
    public ProviderRunnable() {
        //System.out.println("ProviderRunnable init..............");
    }

    @Override
    public void run() {


        //获取队列
        ArrayBlockingQueue queue = QueueManage.getQueue();
        synchronized (this){
            taskId++;
        }
        try {
            System.out.println("产生了一个任务"+taskId);

            queue.put(taskId);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }




}
