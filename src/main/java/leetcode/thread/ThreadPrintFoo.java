package leetcode.thread;

/**
 * 击穿屏障  1 锁  2可见变量   设置一个屏障  设置一个击穿屏障的条件
 * 按顺序打印one two three
 * Description  leetcode.thread
 * Create by  无语
 * Date on  2019/9/18 9:58
 */
public class ThreadPrintFoo {
    public volatile int c;

    public ThreadPrintFoo() {
        c=1;
    }
    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            c=2;
    }

    public void second(Runnable printSecond) throws InterruptedException {

        // printSecond.run() outputs "second". Do not change or remove this line.
                while (c!=2){
                    System.out.println(c);
                }
                printSecond.run();
                c=3;
    }

    public void third(Runnable printThird) throws InterruptedException {

        // printThird.run() outputs "third". Do not change or remove this line.
        while (c!=3){

        }
        printThird.run();
    }


    public static void main(String[] args) throws InterruptedException {
        ThreadPrintFoo threadPrintFoo = new ThreadPrintFoo();
        startThread(threadPrintFoo,"three");
        startThread(threadPrintFoo,"second");
        startThread(threadPrintFoo,"first");
    }

    private static void startThread(ThreadPrintFoo threadPrintFoo, final String a) {
        Thread thread1 = new Thread(  new Runnable() {
            @Override
            public void run() {
                try {

                    if (a.equals("three"))
                        threadPrintFoo.third(new Runnable() {
                            @Override
                            public void run() {
                                System.out.println(a);
                            }
                        });
                    if (a.equals("second"))
                        threadPrintFoo.second(new Runnable() {
                            @Override
                            public void run() {
                                System.out.println(a);
                            }
                        });
                    if (a.equals("first"))
                        threadPrintFoo.first(new Runnable() {
                            @Override
                            public void run() {
                                System.out.println(a);
                            }
                        });




                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.start();
    }

}
