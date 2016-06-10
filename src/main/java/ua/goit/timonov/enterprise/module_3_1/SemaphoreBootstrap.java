package ua.goit.timonov.enterprise.module_3_1;

import java.util.Random;

/**
 * Bootstrap for SimpleSemaphore
 */
public class SemaphoreBootstrap {
    /* available number of permits in semaphore */
    public static final int PERMITS_TOTAL = 10;
    /* number of running threads */
    public static final int N_THREADS = 20;

    public static void main(String[] args) {
        bootstrap();

        java.util.concurrent.Semaphore semy = new java.util.concurrent.Semaphore(5);
        try {
            semy.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // creates object of SimpleSemaphore, runs threads Worker with different number of required permits
    private static void bootstrap() {
        Semaphore semaphore = new SimpleSemaphore(PERMITS_TOTAL);
        Random rand = new Random();
        System.out.println("Semaphore initialized. There are " + semaphore.getAvailablePermits() + " available permits");
        for (int i = 0; i < N_THREADS; i++) {
            int permits = rand.nextInt(PERMITS_TOTAL / 2) + 1;
            new Thread(new Worker(permits, semaphore)).start();
        }
    }
}
