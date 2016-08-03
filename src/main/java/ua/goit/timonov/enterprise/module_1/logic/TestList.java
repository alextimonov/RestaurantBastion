package ua.goit.timonov.enterprise.module_1.logic;

import java.util.List;
import java.util.Random;

/**
 * Abstract class with methods for tests with lists
 */
public abstract class TestList implements Test {

    /* tested list */
    protected List collection;
    /* number of elements */
    protected int nElements;
    /* generator of pseudo random numbers */
    protected Random rand;
    /* measured time for testLock execution */
    private long averageTime;

    public TestList(List collection, int nElements) {
        this.collection = collection;
        this.nElements = nElements;
        rand = new Random();
    }

    /**
     * @return      measured time for this testLock
     */
    public long getAverageTime() {
        return averageTime;
    }

    /**
     * makes testLock for given number of repetitions and finds average time for this testLock
     */
    public void makeTest(){
        long averageTime = 0;
        for (int i = 0; i < REPETITIONS; i++) {
            averageTime += fixTimeOfOperation();
        }
        this.averageTime = averageTime / REPETITIONS;
    }

    /**
     * makes testLock once and measures elapsed time
     * @return          elapsed time for current repetition
     */
    public long fixTimeOfOperation() {
        int index = rand.nextInt(nElements);
        int value = rand.nextInt(Integer.MAX_VALUE);
        long startTime = System.nanoTime();
        makeOperation(index, value);
        long finishTime = System.nanoTime();
        return finishTime - startTime;
    }

    /**
     * makes once only one type of operation
     * @param index     index of element to add, remove etc.
     * @param value     value of added element, element to search etc.
     */
    public void makeOperation(int index, int value) {
    }

}
