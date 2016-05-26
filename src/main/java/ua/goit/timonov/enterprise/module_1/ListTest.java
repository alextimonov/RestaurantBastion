package ua.goit.timonov.enterprise.module_1;

import java.util.List;

/**
 * Created by Alex on 26.05.2016.
 */
public abstract class ListTest <T> {
    public static final int REPETITIONS = 100;

    public long makeTest(List<T> testedList) {
        long averageTime = 0;
        for (int i = 0; i < REPETITIONS; i++) {
            averageTime += fixTimeToPopulate(nElements, value);
        }
        averageTime /= REPETITIONS;
        return averageTime;

        return 0;
        }

    public long findTimeOfPopulate(int nElements, T value) {
        long averageTime = 0;
        for (int i = 0; i < REPETITIONS; i++) {
            averageTime += fixTimeToPopulate(nElements, value);
        }
        averageTime /= REPETITIONS;
        return averageTime;
    }

    public long fixTimeToPopulate(int nElements, T value) {
        testedList.clear();
        long startTime = System.nanoTime();
        for (int i = 0; i < nElements; i++) {
            testedList.add(value);
        }
        long finishTime = System.nanoTime();
        return finishTime - startTime;
    }
}
