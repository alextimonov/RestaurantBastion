package ua.goit.timonov.enterprise.module_1;

import java.util.Set;

/**
 * Implementation of operation add(value) for Set
 */
public class TestSetAdd extends TestSet {
    public <T> TestSetAdd(Set collection, int nElements) {
        super(collection, nElements);
    }

    @Override
    protected void makeOperation(int value) {
        collection.add(value);
    }
}
