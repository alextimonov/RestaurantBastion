package ua.goit.timonov.enterprise.module_3_1;

import java.util.Random;

/**
 * Created by Alex on 08.06.2016.
 */
public class SemaphoreBootstrap {
    public static final int PERMITS_TOTAL = 10;
    public static final int N_THREADS = 20;

    public static void main(String[] args) {
        Semaphore semaphore = new SemaphoreImpl(PERMITS_TOTAL);
        Random rand = new Random();
        System.out.println("Semaphore initialized. There are " + semaphore.getAvailablePermits() + " available permits");
        for (int i = 0; i < N_THREADS; i++) {
            int permits = rand.nextInt(PERMITS_TOTAL / 2) + 1;
            new Thread(new Worker(permits, semaphore)).start();
        }
    }
}
