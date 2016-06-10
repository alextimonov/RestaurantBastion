package ua.goit.timonov.enterprise.module_3_1;

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
        System.out.println("Thread " + Thread.currentThread().getName() +
                " starts working, it requires " + requiredPermits + " permits");
    }

    // receives required number of permits
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

    // simulates thread work delaying it
    private void doSomething() {
        try {
            // do something
            Thread.sleep(DELAY_IN_MILLIS);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    // ends thread work and releases its permits to semaphore
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
