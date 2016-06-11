package ua.goit.timonov.enterprise.module_3_1;

import java.util.logging.Logger;

/**
 * Class simulating some work in the thread
 */
public class Worker implements Runnable {
    /* delay in milliseconds simulating doing something */
    public static final int DELAY_IN_MILLIS = 200;

    /* number of required threads for this thread */
    private int requiredPermits;

    /* solving semaphore */
    private Semaphore semaphore;

    /* static logger */
    private static Logger log = Logger.getLogger(SemaphoreMain.class.getName());

    public Worker(int requiredPermits, Semaphore semaphore) {
        this.requiredPermits = requiredPermits;
        this.semaphore = semaphore;
    }

    /**
     * starts when thread is invoked
     */
    @Override
    public void run() {
        startWorking();
        receiveSemaphorePermits();
        doSomething();
        endWorking();
    }

    // starts thread work
    private void startWorking() {
        log.info("\t\t\t\t\t Thread " + Thread.currentThread().getName() +
                " starts working, it requires " + requiredPermits + " permits");
    }

    // receives required number of permits
    public void receiveSemaphorePermits() {
        log.info("\t\t\t\t\t Thread " + Thread.currentThread().getName() +
                " is trying to get " + requiredPermits + " permits");
        if (requiredPermits > 1) {
            semaphore.acquire(requiredPermits);
            log.info("\t\t\t\t\t Thread " + Thread.currentThread().getName() +
                    " has got " + requiredPermits + " permits");
        }
        else {
            semaphore.acquire();
            log.info("\t\t\t\t\t Thread " + Thread.currentThread().getName() +
                    " has got one permit");
        }
    }

    // simulates thread work delaying it
    private void doSomething() {
        try {
            // do something
            Thread.sleep(DELAY_IN_MILLIS);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
            log.severe(e.getMessage());
        }
    }

    // ends thread work and releases its permits to semaphore
    public void endWorking() {
        if (requiredPermits > 1) {
            semaphore.release(requiredPermits);
        }
        else {
            semaphore.release();
        }
        log.info("\t\t\t\t\t Thread " + Thread.currentThread().getName() + " stops working");
    }
}
