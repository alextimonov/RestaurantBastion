package ua.goit.timonov.enterprise.lectures.lecture_3_1;

/**
 * Created by Alex on 07.06.2016.
 */
public class LatchBootstrap {
    public static final int COUNTER_INIT = 10;
    public static final int N_THREADS = 10;

    private CountDownLatch latch;

    public void test() {
        latch = new CountDownLatch(COUNTER_INIT);
        for (int i = 0; i < N_THREADS; i++) {
            new Thread(new Worker()).start();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (latch.getCounter() > 0) {
                    latch.countDown();
                }
            }
        }).start();
    }

    public class Worker implements Runnable {

        @Override
        public void run() {
            try {
                System.out.println("Thread " + Thread.currentThread().getName() + " starts working...");
                latch.await();
                System.out.println("Thread " + Thread.currentThread().getName() + " stops working...");
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new LatchBootstrap().test();
    }
}
