package ua.goit.timonov.enterprise.module_1;

import java.util.Set;

/**
 * Created by Alex on 27.05.2016.
 */
public class TestSetPopulate extends TestSet {
    public <T> TestSetPopulate(Set collection, int nElements) {
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
