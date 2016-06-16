package ua.goit.timonov.enterprise.lectures.lecture_3_2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Phaser;
import java.util.stream.IntStream;

/**
 * Created by Alex on 15.06.2016.
 */
public class PhaserTesting3 {

    public static void main(String[] args) {
        List<Runnable> tasks = new ArrayList<>();
        IntStream.range(0, 5).forEach((i) -> tasks.add(new Thread(() -> {
            System.out.println(i);
        })));
        new PhaserTesting3().runTasks(tasks);
    }

    void runTasks(List<Runnable> tasks) {
        final Phaser phaser = new Phaser(1); // "1" to register self
        // create and start threads
        for (final Runnable task : tasks) {
            phaser.register();
            new Thread() {
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " created");
                    phaser.arriveAndAwaitAdvance(); // await all creation
                    task.run();
                }
            }.start();
        }

        // allow threads to start and deregister self
        phaser.arriveAndDeregister();
    }
}
