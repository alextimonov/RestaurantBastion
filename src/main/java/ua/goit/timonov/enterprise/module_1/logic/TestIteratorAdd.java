package ua.goit.timonov.enterprise.module_1.logic;

import java.util.List;
import java.util.ListIterator;

/**
 * Implementation of operation iterator.add(value) for list
 */
public class TestIteratorAdd <T> extends TestList {
    public <T> TestIteratorAdd(List<T> collection, int nElements) {
        super(collection, nElements);
    }

    @Override
    public long fixTimeOfOperation() {
        ListIterator<T> iterator = collection.listIterator();
        int index = rand.nextInt(nElements);
        Integer valueInt = rand.nextInt(Integer.MAX_VALUE);
        T value = (T) valueInt;

        for (int i = 0; i < index; i++) {
            iterator.next();
        }
        long startTime = System.nanoTime();
        iterator.add(value);
        long finishTime = System.nanoTime();
        return finishTime - startTime;
    }
}
