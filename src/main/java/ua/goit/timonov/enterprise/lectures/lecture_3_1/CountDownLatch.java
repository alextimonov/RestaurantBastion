package ua.goit.timonov.enterprise.lectures.lecture_3_1;

/**
 * Created by Alex on 06.06.2016.
 */
public class CountDownLatch {
    private int counter = 0;
    private final Object lock = new Object();

    public CountDownLatch(int counter) {
        this.counter = counter;
    }

    public int getCounter() {
        synchronized (lock) {
            return counter;
        }
    }

    public void await() throws InterruptedException {
//        while (true) {
        synchronized (lock) {
            if (counter > 0) {
                lock.wait();
            }
        }
    }
//                else {
//                    lock.notify();
//                     a lot of code here
//                    break;
//                }
//        }

    public void countDown() {
        synchronized (lock) {
            if (counter > 0) {
                counter--;
                System.out.println("Counter = " + counter);
            }
            if (counter == 0) {
//                lock.notify();
                lock.notifyAll();
            }
        }
    }
}
