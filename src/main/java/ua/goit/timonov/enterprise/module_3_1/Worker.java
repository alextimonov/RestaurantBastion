package ua.goit.timonov.enterprise.module_3_1;

/**
 * Created by Alex on 08.06.2016.
 */
public class Worker implements Runnable {
    public static final int DELAY_IN_MILLIS = 200;
    private int requiredPermits;
    private Semaphore semaphore;

    public Worker(int requiredPermits, Semaphore semaphore) {
        this.requiredPermits = requiredPermits;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        startWorking();
        receiveSemaphorePermits();
        doSomething();
        endWorking();
    }

    private void startWorking() {
        System.out.println("Thread " + Thread.currentThread().getName() +
                " starts working, it requires " + requiredPermits + " permits");
    }

    private void receiveSemaphorePermits() {
        System.out.println("Thread " + Thread.currentThread().getName() +
                " is trying to get permits");
        if (requiredPermits > 1) {
            semaphore.acquire(requiredPermits);
        }
        else {
            semaphore.acquire();
        }
        System.out.println("Thread " + Thread.currentThread().getName() +
                " has get permits");
    }

    private void doSomething() {
        try {
            // do something
            Thread.sleep(DELAY_IN_MILLIS);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void endWorking() {
        if (requiredPermits > 1) {
            semaphore.release(requiredPermits);
        }
        else {
            semaphore.release();
        }
        System.out.println("Thread " + Thread.currentThread().getName() + " stops working");
    }
}
