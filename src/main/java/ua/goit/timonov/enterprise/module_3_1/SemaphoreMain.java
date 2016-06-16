package ua.goit.timonov.enterprise.module_3_1;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.*;

/**
 * Bootstrap for SimpleSemaphore
 */
public class SemaphoreMain {
    /* available number of permits in semaphore */
    public static final int PERMITS_TOTAL = 10;
    /* number of running threads */
    public static final int N_THREADS = 20;

//    public static final String OUT_PATH_TO_LOG_FILE = "out/production/module_3_1/semaphore.log";
    public static final String PATH_TO_LOG_FILE = "resources/log/module_3_1/semaphore.log";

    /* static logger */
    private static Logger log = Logger.getLogger(SemaphoreMain.class.getName());

    public static void main(String[] args) {
        try {
            configureLogger();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Semaphore semaphore = initializeSemaphore();
        runThreadsForSemaphore(semaphore);
    }

    // configures logger, redirects System.out & System.err to the log file
    private static void configureLogger() throws IOException {
        Handler fileHandler = new FileHandler(PATH_TO_LOG_FILE, false);
        fileHandler.setFormatter(new SimpleFormatter());
        log.addHandler(fileHandler);

        PrintStream printStream =
                new PrintStream(
                        new BufferedOutputStream(
                                new FileOutputStream(PATH_TO_LOG_FILE, false)));
        System.setErr(printStream);
        System.setOut(printStream);
    }

    // creates object of SimpleSemaphore, runs threads Worker with different number of required permits
    private static Semaphore initializeSemaphore() {
        Semaphore semaphore = new SimpleSemaphore(PERMITS_TOTAL);
        log.info("Semaphore is initialized. There are " + semaphore.getAvailablePermits() + " available permits");
        return semaphore;
    }

    // creates object of SimpleSemaphore, runs threads Worker with different number of required permits
    private static void runThreadsForSemaphore(Semaphore semaphore) {
        Random rand = new Random();
        for (int i = 0; i < N_THREADS; i++) {
            int permits = rand.nextInt(PERMITS_TOTAL / 2) + 1;
            new Thread(new Worker(permits, semaphore)).start();
        }
        ReentrantLock lock = new ReentrantLock();
        try {
            lock.tryLock(100, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
