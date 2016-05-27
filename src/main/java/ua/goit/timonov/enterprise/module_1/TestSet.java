package ua.goit.timonov.enterprise.module_1;

import java.util.Random;
import java.util.Set;

/**
 * Created by Alex on 27.05.2016.
 */
abstract class TestSet<T> {
    public static final int REPETITIONS = 20;

    protected Set<T> collection;
    protected int nElements;
    protected Random rand = new Random();
    private long elapsedTime;

    public TestSet(Set<T> collection, int nElements) {
        this.collection = collection;
        this.nElements = nElements;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

    public void makeTest(){
        long averageTime = 0;
        for (int i = 0; i < REPETITIONS; i++) {
            averageTime += fixTimeOfOperation();
        }
        elapsedTime = averageTime / REPETITIONS;
    }

    public long fixTimeOfOperation() {
        int value = rand.nextInt(Integer.MAX_VALUE);
        long startTime = System.nanoTime();
        makeOperation(value);
        long finishTime = System.nanoTime();
        return finishTime - startTime;
    }

    protected void makeOperation(int value) {
    }
}
