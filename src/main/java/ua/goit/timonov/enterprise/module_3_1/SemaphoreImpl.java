package ua.goit.timonov.enterprise.module_3_1;

/**
 * Implementation of interface Semaphore
 */
public class SemaphoreImpl implements Semaphore {
    private int permitsTotal;
    private int issuedPermits;
    private final Object lock = new Object();

    public SemaphoreImpl(int permitsTotal) {
        this.permitsTotal = permitsTotal;
    }

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
            System.out.println("\t\t\t\t\t\t\t\t\t\tThread " + Thread.currentThread().getName() + " received 1 permit,  " +
                    getAvailablePermits() + " permits are available now");
        }
    }

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
            System.out.println("\t\t\t\t\t\t\t\t\t\tThread " + Thread.currentThread().getName() + " received " + permits + " permits, " +
                    getAvailablePermits() + " permits are available now");
        }
    }

    @Override
    public void release() {
        synchronized (lock) {
            issuedPermits--;
            System.out.println("\t\t\t\t\t\t\t\t\t\tThread " + Thread.currentThread().getName() + " released 1 permit,  " +
                    getAvailablePermits() + " permits are available now");
            lock.notifyAll();
        }
    }

    @Override
    public void release(int permits) {
        synchronized (lock) {
            issuedPermits -= permits;
            System.out.println("\t\t\t\t\t\t\t\t\t\tThread " + Thread.currentThread().getName() + " released " + permits + " permits, " +
                    getAvailablePermits() + " permits are available now");
            lock.notifyAll();
        }
    }

    @Override
    public int getAvailablePermits() {
        synchronized (lock) {
            return permitsTotal - issuedPermits;
        }
    }
}
