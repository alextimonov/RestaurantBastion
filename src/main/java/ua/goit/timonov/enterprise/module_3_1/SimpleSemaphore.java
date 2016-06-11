package ua.goit.timonov.enterprise.module_3_1;

import java.util.logging.Logger;

/**
 * Implementation of interface Semaphore
 */
public class SimpleSemaphore implements Semaphore {

    /* total number of semaphore permits */
    private int permitsTotal;

    /* number of issued permits, should be in diapason [0..permitsTotal] */
    private int issuedPermits;

    /* locker for thread synchronizing  */
    private final Object lock = new Object();

    // static logger
    private static Logger log = Logger.getLogger(SemaphoreMain.class.getName());

    public SimpleSemaphore(int permitsTotal) {
        this.permitsTotal = permitsTotal;
    }

    /**
     * Requires a permit from semaphore. If there is free permit, acquires it.
     * If not - suspends the thread until free permit will appear
     */
    @Override
    public void acquire() {
        synchronized (lock) {
            while (issuedPermits >= permitsTotal) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            issuedPermits++;
            log.info("Thread " + Thread.currentThread().getName() + " received 1 permit,  " +
                    getAvailablePermits() + " permits are available now");
        }
    }

    /**
     * Requires given number of permits from semaphore. If there are required number of free permits, acquires them.
     * If not - suspends the thread until required number of free permits will appear
     * @param permits   required permits
     */
    @Override
    public void acquire(int permits) {
        synchronized (lock) {
            while (issuedPermits + permits > permitsTotal ) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            issuedPermits += permits;
            log.info("Thread " + Thread.currentThread().getName() + " received " + permits + " permits, " +
                    getAvailablePermits() + " permits are available now");
        }
    }

    /**
     * Releases permit and returns it to semaphore
     */
    @Override
    public void release() {
        synchronized (lock) {
            issuedPermits--;
            log.info("Thread " + Thread.currentThread().getName() + " released 1 permit,  " +
                    getAvailablePermits() + " permits are available now");
            lock.notifyAll();
        }
    }

    /**
     * Releases given number of permits and returns them to semaphore
     * @param permits       number of permits to release
     */
    @Override
    public void release(int permits) {
        synchronized (lock) {
            issuedPermits -= permits;
            log.info("Thread " + Thread.currentThread().getName() + " released " + permits + " permits, " +
                    getAvailablePermits() + " permits are available now");
            lock.notifyAll();
        }
    }

    /**
     * returns available number of permits
     * @return      available number of permits
     */
    @Override
    public int getAvailablePermits() {
        synchronized (lock) {
            return permitsTotal - issuedPermits;
        }
    }
}
