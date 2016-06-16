package ua.goit.timonov.enterprise.module_3_2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Evaluates square sum of elements in the array dividing overall task in given number of threads using Phaser synchronizer
 */
public class PhaserSquareSum implements SquareSum {

    /* array with number of elements in segments of base array */
    private int[] elementsInSegments;

    /* getter */
    public int[] getElementsInSegments() {
        return elementsInSegments;
    }

    /**
     * finds square sum of elements in received array overall task in given number of threads using Phaser synchronizer
     * @param values                received array of int values
     * @param numberOfThreads       number of threads (tasks) to divide overall task
     * @return                      square sum of elements in received array
     * @throws IllegalArgumentException if arguments are not valid
     */
    @Override
    public long getSquareSum(int[] values, int numberOfThreads) {
        final Phaser phaser = new Phaser();
        validateArguments(values.length, numberOfThreads);
        findNElementsInSegment(values.length, numberOfThreads);
        List<Callable<Long>> callables = formCallableTasks(values, numberOfThreads, phaser);

        ExecutorService executor = Executors.newCachedThreadPool();
        List<Future<Long>> segmentResults;
        long overallResult = 0L;
        try {
            segmentResults = executor.invokeAll(callables);
            for (Future<Long> segmentResult : segmentResults) {
                overallResult += segmentResult.get();
            }
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        executor.shutdown();
        return overallResult;
    }

    // forms list of callable tasks for each segment
    private List<Callable<Long>> formCallableTasks(int[] values, int numberOfThreads, Phaser phaser) {
        List<Callable<Long>> callables = new ArrayList<>();
        int startPosition = 0;
        for (int i = 0; i < numberOfThreads; i++) {
            int[] arraySegment = new int[elementsInSegments[i]];
            System.arraycopy(values, startPosition, arraySegment, 0, elementsInSegments[i]);
            callables.add(new SegmentTask(phaser, arraySegment));
            startPosition += elementsInSegments[i];
        }
        return callables;
    }

    // validates received arguments
    private void validateArguments(int length, int numberOfThreads) {
        if (length == 0) {
            throw new IllegalArgumentException("Array with values should contain elements!");
        }
        if (numberOfThreads <= 0) {
            throw new IllegalArgumentException("Number of threads should be positive!");
        }
    }

    // forms array with numebr of elements in each array segment
    private void findNElementsInSegment(int length, int numberOfThreads) {
        elementsInSegments = new int[numberOfThreads];
        int quotient = length / numberOfThreads;
        int modulo = length % numberOfThreads;
        for (int i = 0; i < numberOfThreads; i++) {
            elementsInSegments[i] = i < modulo ? quotient + 1 : quotient;
        }
    }
}


    /*public static void main(String[] args) {
        PhaserSquareSum launcher = new PhaserSquareSum();
        int[] values = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        int numberOfThreads = 4;
        launcher.getSquareSum(values, numberOfThreads);
    }*/


/*int num = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("End of phase # " + num);

        num = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("End of phase # " + num);

        num = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("End of phase # " + num);

        phaser.arriveAndDeregister();
        if (phaser.isTerminated())
            System.out.println("The phaser is terminated");*/



//        List<Callable<Long>> callables = new ArrayList<>();
//        ExecutorService executor = Executors.newSingleThreadExecutor();
//        String result = executor.invokeAny(callables);
//        System.out.println(result);
//        executor.shutdown();


 /*List<Callable<Long>> callables = new ArrayList<>();
        IntStream.range(0, numberOfThreads).forEach(i -> callables.add(() -> Long.valueOf(values[i])));
//        IntStream.range(0, numberOfThreads).forEach(i -> callables.add(() -> Long.valueOf(i) ));

        ExecutorService executor = Executors.newCachedThreadPool();
        List<Future<Long>> result = null;

        try {
            result = executor.invokeAll(callables);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Long calcResult = 0L;
        for (Future f : result) {
            try {
                calcResult += (Long) f.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();

        return calcResult;*/