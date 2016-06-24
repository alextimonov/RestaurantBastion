package ua.goit.timonov.enterprise.module_3_2;

import java.util.concurrent.Callable;
import java.util.concurrent.Phaser;

/**
 * Task for phase evaluation of square sum of elements in received array segment with other tasks
 */
public class SegmentTask implements Callable<Long> {
    private Phaser phaser;
    private int arraySegment[];

    public SegmentTask(Phaser phaser, int[] arraySegment) {
        this.phaser = phaser;
        this.arraySegment = arraySegment;
    }

    @Override
    public Long call() throws Exception {
        phaser.register();

        long result = 0L;
        for (int element : arraySegment) {
            result += element * element;
        }
        phaser.arriveAndAwaitAdvance();

        // some another job
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        phaser.arriveAndDeregister();
        return result;
    }
}
