package ua.goit.timonov.enterprise.lectures.lecture_3_2;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Exchanger;
import java.util.stream.IntStream;

/**
 * Created by Alex on 14.06.2016.
 */
public class Synchronizers {

    public static void main(String[] args) {
        barrierTesting();
//        exchangerTesting();
    }

    private static void barrierTesting() {
        CyclicBarrier barrier = new CyclicBarrier(5, () -> System.out.println("Barrier exceeded"));
        while (true) {
            new Thread(() -> {
                try {
                    String threadName = Thread.currentThread().getName();
                    System.out.println(threadName + " starts waiting on barrier.");
                    barrier.await();
                    System.out.println(threadName + " finishes waiting");
                } catch (InterruptedException | BrokenBarrierException e ) {
                    e.printStackTrace();
                }

            }).start();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void exchangerTesting() {

        Exchanger<String> exchanger = new Exchanger<>();
        Random random = new Random();

        IntStream.range(0, 2).forEach( (i) -> new Thread( () ->  {
            try {
                Thread.sleep(random.nextInt(1000));
                String threadName = Thread.currentThread().getName();
                System.out.println(threadName + " ready to exchange");
                System.out.println(threadName + " < - > " + exchanger.exchange(threadName));
                exchanger.exchange(threadName);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
    }

}
