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
        String threadName = Thread.currentThread().getName();
        phaser.register();

        long result = 0L;
        for (int element : arraySegment) {
            result += element * element;
        }
        System.out.println(threadName + " evaluates its task, phase = " + phaser.getPhase() +
                ", parties = " + phaser.getRegisteredParties() + ", arrived = " + phaser.getArrivedParties() +
                ", unarrived = " + phaser.getUnarrivedParties());
        phaser.arriveAndAwaitAdvance();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(threadName + " ends, result = " + result + ", phase = " + phaser.getPhase() +
                ", parties = " + phaser.getRegisteredParties() + ", arrived = " + phaser.getArrivedParties() +
                ", unarrived = " + phaser.getUnarrivedParties());
        phaser.arriveAndDeregister();
        return result;
    }
}
