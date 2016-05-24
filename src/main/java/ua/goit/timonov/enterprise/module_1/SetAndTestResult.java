package ua.goit.timonov.enterprise.module_1;

import java.util.Set;

/**
 * Created by Alex on 22.05.2016.
 */
public class SetAndTestResult<T> {

    private Set<T> testedSet;

    public long fixTimeToAdd(T value) {
        long startTime = System.nanoTime();
        testedSet.add(value);
        long finishTime = System.nanoTime();
        return finishTime - startTime;
    }

    public long fixTimeToRemove(T value) {
        long startTime = System.nanoTime();
        testedSet.remove(value);
        long finishTime = System.nanoTime();
        return finishTime - startTime;
    }

    public long fixTimeToContains(T value) {
        long startTime = System.nanoTime();
        testedSet.contains(value);
        long finishTime = System.nanoTime();
        return finishTime - startTime;
    }

    public long fixTimeToPopulate(int nElems, T value) {
        long startTime = System.nanoTime();
        for (int i = 0; i < nElems; i++) {
            testedSet.add(value);
        }
        long finishTime = System.nanoTime();
        return finishTime - startTime;
    }

}
