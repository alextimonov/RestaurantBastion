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
