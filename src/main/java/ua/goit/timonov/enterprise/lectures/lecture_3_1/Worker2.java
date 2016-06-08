package ua.goit.timonov.enterprise.lectures.lecture_3_1;

/**
 * Created by Alex on 07.06.2016.
 */
public class Worker2 implements Runnable {

    @Override
    public void run() {
        try {
            System.out.println("Thread " + Thread.currentThread().getName() + " starts working...");
//            CountDownLatch.await();

        }
        catch (Exception e) {
        }
    }
}
