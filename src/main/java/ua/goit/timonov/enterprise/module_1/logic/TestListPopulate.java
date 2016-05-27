package ua.goit.timonov.enterprise.module_1.logic;

import java.util.List;

/**
 * Implementation of operation populate() for list
 */
public class TestListPopulate extends TestList {

    public <T> TestListPopulate(List<T> collection, int nElements) {
        super(collection, nElements);
    }

    @Override
    public long fixTimeOfOperation() {
        collection.clear();
        long startTime = System.nanoTime();
        for (int i = 0; i < nElements; i++) {
            collection.add(rand.nextInt(Integer.MAX_VALUE));
        }
        long finishTime = System.nanoTime();
        return finishTime - startTime;
    }
}
