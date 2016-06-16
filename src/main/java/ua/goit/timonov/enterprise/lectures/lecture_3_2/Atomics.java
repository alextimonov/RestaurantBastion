package ua.goit.timonov.enterprise.lectures.lecture_3_2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Alex on 14.06.2016.
 */
public class Atomics {
    private static AtomicInteger counter = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        new Atomics().test();
    }

    public static int increment() {
        return counter.incrementAndGet();
    }

    private void test() throws InterruptedException {
        List<Aggregator> aggregators = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Aggregator aggregator = new Aggregator();
            aggregators.add(aggregator);
            new Thread(aggregator).start();
        }

        Thread.sleep(100);
        boolean isValid = true;
        Set<Integer> integerSet = new HashSet<>();
        for (Aggregator aggregator : aggregators) {
            for (Integer anInt : aggregator.ints) {
                if (!integerSet.add(anInt)) {
                    System.out.println("Error! Duplicate is found: " + anInt);
                    isValid = false;
                }
            }
        }
        if (isValid)
            System.out.println("No duplicates.");
    }

    public class Aggregator implements Runnable{
//        private SynchronizedExample synchExample = new SynchronizedExample();
        private List<Integer> ints = new ArrayList<>();

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
//                ints.add(synchExample.increment());
                ints.add(increment());
            }
        }
    }

//    public class SynchronizedExample {
//        private int counter;
//
//        public int increment() {
//            return counter++;
//        }
//    }
}
