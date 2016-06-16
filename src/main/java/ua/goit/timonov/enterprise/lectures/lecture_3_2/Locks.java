package ua.goit.timonov.enterprise.lectures.lecture_3_2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * Created by Alex on 14.06.2016.
 */
public class Locks {

    // true == fair policy, but works slowly
    private final Lock lock = new ReentrantLock(true);

    public static void main(String[] args) {
        final Locks locks = new Locks();

        // uses lambda and IntSream
        IntStream.range(0, 10).forEach(i -> new Thread(locks::testTryLock));

//         common loop
//        for (int i = 0; i < 10; i++) {
//            lambda style with method reference
//            new Thread(locks::testTryLock).start();
//        }

//            lambda style
//            new Thread(() -> {
//                locks.testTryLock();
//            }).start();

//            old style
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    // locks.testLock();
//                    locks.testTryLock();
//                }
//            }).start();
    }

    public void testLock() {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " tries to lock! ");

//        // to interrupt the thread waiting on the lock
//        try {
//            lock.lockInterruptibly();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        lock.lock();
        try {
            System.out.println(threadName + " is executing critical section.");
            Thread.sleep(100);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            System.out.println(threadName + " releases lock");
            lock.unlock();
        }
    }

    public void testTryLock() {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " tries to lock! ");
        try {
            if (lock.tryLock(100, TimeUnit.MILLISECONDS)) {
                try {
                    System.out.println(threadName + " is executing critical section.");
                    Thread.sleep(20);
                } finally {
                    System.out.println(threadName + " releases lock");
                    lock.unlock();
                }
            }
            else {
                System.out.println(threadName + " unable to acquire lock!");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
