package ua.goit.timonov.enterprise.lectures.lecture_3_1;

/**
 * Created by Alex on 05.06.2016.
 */


public class Test {
    // Test #1
    /*private  int counter = 0;
    private  final Object lock = new Object();

    public  void await() throws InterruptedException {
        synchronized(lock) {
            if (counter > 0) {
                lock.wait();
            }
        }
    }

    public static void main(String[] args) {
        try {
            new Test().await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/

    // Test #2
    public static volatile boolean flag = true;
//    ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            public void run() {
                int i = 0;
                while (flag) {
                    i++;
                }
                System.out.println(i);
            }
        }).start();
        Thread.sleep(100);
        flag = false;
    }
}
