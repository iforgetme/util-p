package mq.util;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Description  mq.util
 * Create by  无语
 * Date on  2019/9/17 13:54
 */
public class QueueManage {


    /**
     * 初始化大小
     */
    private static int capacity=10;
    /**
     * jdk提供的基于数组实现的阻塞队列
     */
    private  static ArrayBlockingQueue  queue =new ArrayBlockingQueue(capacity);

    public static  ArrayBlockingQueue getQueue(){

        return  queue;
    }





}
