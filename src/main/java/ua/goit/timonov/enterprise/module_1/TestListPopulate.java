package ua.goit.timonov.enterprise.module_1;

import java.util.List;

/**
 * Created by Alex on 26.05.2016.
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
