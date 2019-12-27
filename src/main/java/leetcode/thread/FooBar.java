package leetcode.thread;

import thread.ThreadUtils;

import java.util.concurrent.Semaphore;

/**
 *
 *  信号量   同步锁   重入锁  奇偶.....
 *  循环打印for bar
 * Description  leetcode.thread
 * Create by  无语
 * Date on  2019/9/18 10:58
 */
public class FooBar {
        private int n;

        public FooBar(int n) {
            this.n = n;
        }
        //
        private Semaphore foo = new Semaphore(1);
        private Semaphore bar = new Semaphore(0);

        public void foo(Runnable printFoo) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                    // printFoo.run() outputs "foo". Do not change or remove this line.
                foo.acquire();
                printFoo.run();
                bar.release();

            }
        }

        public void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0; i < n; i++) {

                // printBar.run() outputs "bar". Do not change or remove this line.
                     bar.acquire();
                    printBar.run();
                    foo.release();
            }
        }


    public static void main(String[] args) throws InterruptedException {
        FooBar fooBar = new FooBar(3);

        ThreadUtils.startThread( new Runnable() {
            @Override
            public void run() {
                try {
                    fooBar.bar(getRun("bar"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        ThreadUtils.startThread( new Runnable() {
            @Override
            public void run() {
                try {
                    fooBar.foo(getRun("foo"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public static Runnable getRun(final  String abc){
            return  new Runnable() {
                @Override
                public void run() {
                    System.out.println(abc);
                }
            };
    }






}
