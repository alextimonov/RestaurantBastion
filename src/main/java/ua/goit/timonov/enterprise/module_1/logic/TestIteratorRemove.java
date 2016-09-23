package ua.goit.timonov.enterprise.module_1.logic;

import java.util.List;
import java.util.ListIterator;

/**
 * Implementation of operation iterator.remove() for list
 */
public class TestIteratorRemove extends TestList {

    public TestIteratorRemove(List collection, int nElements) {
        super(collection, nElements);
    }

    @Override
    public long fixTimeOfOperation() {
        ListIterator iterator = collection.listIterator();
        int index = rand.nextInt(nElements);

        for (int i = 0; i < index; i++) {
            iterator.next();
        }
        long startTime = System.nanoTime();
        iterator.remove();
        long finishTime = System.nanoTime();
        return finishTime - startTime;
    }
}
