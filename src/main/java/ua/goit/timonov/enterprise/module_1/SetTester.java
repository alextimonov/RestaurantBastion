package ua.goit.timonov.enterprise.module_1;

import java.util.Set;

/**
 * Created by Alex on 24.05.2016.
 */
public class SetTester<T> {

    public static final int REPETITIONS = 20;
    private Set<T> testedSet;

    public SetTester(Set<T> testedSet) {
        this.testedSet = testedSet;
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
        long startTime = System.nanoTime();
        for (int i = 0; i < nElements; i++) {
            testedSet.add(value);
        }
        long finishTime = System.nanoTime();
        return finishTime - startTime;
    }

    public long findTimeOfAdd(int nElements, T value) {
        long averageTime = 0;
        for (int i = 0; i < REPETITIONS; i++) {
            averageTime += fixTimeToAdd(nElements/2, value);
        }
        averageTime /= REPETITIONS;
        return averageTime;
    }

    public long fixTimeToAdd(int index, T elem) {
        long startTime = System.nanoTime();
        testedSet.add(elem);
        long finishTime = System.nanoTime();
        return finishTime - startTime;
    }

    public long findTimeOfRemove(int nElements) {
        long averageTime = 0;
        for (int i = 0; i < REPETITIONS; i++) {
            averageTime += fixTimeToRemove(nElements/2);
        }
        averageTime /= REPETITIONS;
        return averageTime;
    }

    public long fixTimeToRemove(int index) {
        long startTime = System.nanoTime();
        boolean removed = testedSet.remove(index);
        long finishTime = System.nanoTime();
        return finishTime - startTime;
    }

    public long findTimeOfContains(int nElements, T value) {
        long averageTime = 0;
        for (int i = 0; i < REPETITIONS; i++) {
            averageTime += fixTimeToContains(value);
        }
        averageTime /= REPETITIONS;
        return averageTime;
    }

    public long fixTimeToContains(T value) {
        long startTime = System.nanoTime();
        boolean isElementInList = testedSet.contains(value);
        long finishTime = System.nanoTime();
        return finishTime - startTime;
    }
}
