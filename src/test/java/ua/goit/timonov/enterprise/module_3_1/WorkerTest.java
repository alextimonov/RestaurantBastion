package ua.goit.timonov.enterprise.module_3_1;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Testing class for Worker and SimpleSemaphore
 */
public class WorkerTest {
    private Semaphore semaphore;

    @Rule
    public Timeout timeout = new Timeout(500, TimeUnit.MILLISECONDS);

    @Before
    public void setUp() throws Exception {
        semaphore = new SimpleSemaphore(5);
    }


    @Test
    public void testReceiveSemaphorePermitsNormal_1() {
        Worker worker = new Worker(3, semaphore);
        worker.receiveSemaphorePermits();
        int availablePermits = semaphore.getAvailablePermits();
        assertEquals(availablePermits, 2);
    }

    @Test
    public void testReceiveSemaphorePermitsNormal_2() {
        Worker worker = new Worker(5, semaphore);
        worker.receiveSemaphorePermits();
        int availablePermits = semaphore.getAvailablePermits();
        assertEquals(availablePermits, 0);
    }

    @Test
    public void testReceiveSemaphorePermitsNormal_3() {
        Worker worker = new Worker(1, semaphore);
        worker.receiveSemaphorePermits();
        int availablePermits = semaphore.getAvailablePermits();
        assertEquals(availablePermits, 4);
    }

    @Ignore
    @Test
    public void testReceiveSemaphorePermitsTimeoutAchieved() {
        Worker worker = new Worker(6, semaphore);
        worker.receiveSemaphorePermits();
        int availablePermits = semaphore.getAvailablePermits();
        assertEquals(availablePermits, 5);
    }

    @Test
    public void testEndWorkingNormal_1() {
        Worker worker1 = new Worker(3, semaphore);
        worker1.receiveSemaphorePermits();

        Worker worker2 = new Worker(2, semaphore);
        worker2.receiveSemaphorePermits();

        worker1.endWorking();

        int availablePermits = semaphore.getAvailablePermits();
        assertEquals(availablePermits, 3);
    }

    @Test
    public void testEndWorkingNormal_2() {
        Worker worker1 = new Worker(1, semaphore);
        worker1.receiveSemaphorePermits();

        Worker worker2 = new Worker(3, semaphore);
        worker2.receiveSemaphorePermits();

        worker1.endWorking();

        int availablePermits = semaphore.getAvailablePermits();
        assertEquals(availablePermits, 2);
    }

    @Test
    public void testEndWorkingNormal_3() {
        Worker worker1 = new Worker(2, semaphore);
        worker1.receiveSemaphorePermits();

        Worker worker2 = new Worker(3, semaphore);
        worker2.receiveSemaphorePermits();

        worker1.endWorking();
        worker2.endWorking();


        int availablePermits = semaphore.getAvailablePermits();
        assertEquals(availablePermits, 5);
    }
}