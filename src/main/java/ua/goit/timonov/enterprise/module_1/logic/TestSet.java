package ua.goit.timonov.enterprise.module_1.logic;

import java.util.Random;
import java.util.Set;

/**
 * Abstract class with methods for tests with sets
 */
abstract class TestSet implements Test {

    /* tested Set */
    protected Set collection;
    /* number of elements */
    protected int nElements;
    /* generator of pseudorandom numbers */
    protected Random rand = new Random();
    /* measured time for test execution */
    private long averageTime;

    public TestSet(Set collection, int nElements) {
        this.collection = collection;
        this.nElements = nElements;
    }

    /**
     * @return      measured time for this test
     */
    public long getAverageTime() {
        return averageTime;
    }

    /**
     * makes test for given number of repetitions and finds average time for this test
     */
    public void makeTest(){
        long averageTime = 0;
        for (int i = 0; i < REPETITIONS; i++) {
            averageTime += fixTimeOfOperation();
        }
        this.averageTime = averageTime / REPETITIONS;
    }

    /**
     * makes test once and measures elapsed time
     * @return          elapsed time for current repetition
     */
    public long fixTimeOfOperation() {
        int value = rand.nextInt(Integer.MAX_VALUE);
        long startTime = System.nanoTime();
        makeOperation(value);
        long finishTime = System.nanoTime();
        return finishTime - startTime;
    }

    /**
     * makes once only one type of operation
     * @param index     index of element to add, remove etc.
     * @param value     value of added element, element to find etc.
     */
    public void makeOperation(int value, int index) {
        makeOperation(value);
    }

    /**
     * makes once only one type of operation
     * @param value     value of added element, element to find etc.
     */
    protected void makeOperation(int value) {
    }
}
