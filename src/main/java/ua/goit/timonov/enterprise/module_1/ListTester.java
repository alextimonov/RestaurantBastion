package ua.goit.timonov.enterprise.module_1;

import java.util.List;
import java.util.ListIterator;

/**
 * Class with fixing time of operations with list
 */
public class ListTester<T> {
    public static final int REPETITIONS = 20;
    private List<T> testedList;

    public ListTester(List<T> testedList) {
        this.testedList = testedList;
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
            testedList.add(value);
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
        testedList.add(index, elem);
        long finishTime = System.nanoTime();
        return finishTime - startTime;
    }

    public long findTimeOfGet(int nElements) {
        long averageTime = 0;
        for (int i = 0; i < REPETITIONS; i++) {
            averageTime += fixTimeToGet(nElements/2);
        }
        averageTime /= REPETITIONS;
        return averageTime;
    }

    public long fixTimeToGet(int index) {
        long startTime = System.nanoTime();
        T elem = testedList.get(index);
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
        T elem = testedList.remove(index);
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
        boolean isElementInList = testedList.contains(value);
        long finishTime = System.nanoTime();
        return finishTime - startTime;
    }

    public long findTimeOfListIteratorAdd(int nElements, T value) {
        long averageTime = 0;
        for (int i = 0; i < REPETITIONS; i++) {
            averageTime += fixTimeToIteratorAdd(nElements/2, value);
        }
        averageTime /= REPETITIONS;
        return averageTime;
    }

    public long fixTimeToIteratorAdd(int index, T value) {
        ListIterator<T> iterator = testedList.listIterator();
        for (int i = 0; i < index; i++) {
            iterator.next();
        }
        long startTime = System.nanoTime();
        iterator.add(value);
        long finishTime = System.nanoTime();
        return finishTime - startTime;
    }

    public long findTimeOfIteratorRemove(int nElements) {
        long averageTime = 0;
        for (int i = 0; i < REPETITIONS; i++) {
            averageTime += fixTimeToIteratorRemove(nElements/2);
        }
        averageTime /= REPETITIONS;
        return averageTime;
    }

    public long fixTimeToIteratorRemove(int index) {
        ListIterator<T> iterator = testedList.listIterator();
        for (int i = 0; i < index; i++) {
            iterator.next();
        }
        long startTime = System.nanoTime();
        iterator.remove();
        long finishTime = System.nanoTime();
        return finishTime - startTime;
    }
}
