package ua.goit.timonov.enterprise.module_3_2;

/**
 * Evaluates square sum of elements in the array dividing overall task in given number of threads
 */
public interface SquareSum {

    long getSquareSum(int[] values, int numberOfThreads);

    int[] getElementsInSegments();
}
