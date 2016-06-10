package ua.goit.timonov.enterprise.module_3_1;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Testing class for SimpleSemaphore
 */
public class SimpleSemaphoreTest {
    public static final int PERMITS_TOTAL = 10;
    Semaphore semaphore = new SimpleSemaphore(PERMITS_TOTAL);

    @Test
    public void testAcquire() {
        Worker worker = new Worker(4, semaphore);
        new Thread(worker).start();
    }

    @Test
    public void testAcquire1() throws Exception {

    }

    @Test
    public void testRelease() throws Exception {

    }

    @Test
    public void testRelease1() throws Exception {

    }

    @Test
    public void testGetAvailablePermits() throws Exception {

    }
}