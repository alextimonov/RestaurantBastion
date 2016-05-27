package ua.goit.timonov.enterprise.module_1;

import java.util.List;
import java.util.Random;

/**
 * Created by Alex on 26.05.2016.
 */
public abstract class TestList<T> {
    public static final int REPETITIONS = 20;

    protected List<T> collection;
    protected int nElements;
    protected Random rand = new Random();
    private long elapsedTime;

    public TestList(List<T> collection, int nElements) {
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
        int index = rand.nextInt(nElements);
        int value = rand.nextInt(Integer.MAX_VALUE);
        long startTime = System.nanoTime();
        makeOperation(index, value);
        long finishTime = System.nanoTime();
        return finishTime - startTime;
    }

    protected void makeOperation(int index, int value) {
    }
}
