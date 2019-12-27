package mq.customer;

import mq.util.QueueManage;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Description  mq.customer
 * Create by  无语
 * Date on  2019/9/17 15:03
 */
public class Customer implements  Runnable {
    @Override
    public void run() {
        //模拟消费者    可以开个守护线程一直扫描队列
        ArrayBlockingQueue queue = QueueManage.getQueue();
        Object peek = queue.poll();
        System.out.println("消费了一个任务！任务Id："+peek);


    }
}
