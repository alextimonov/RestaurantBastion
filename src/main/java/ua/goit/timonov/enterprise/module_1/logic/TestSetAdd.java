package ua.goit.timonov.enterprise.module_1.logic;

import java.util.Set;

/**
 * Implementation of operation add(value) for Set
 */
public class TestSetAdd extends TestSet {
    public TestSetAdd(Set collection, int nElements) {
        super(collection, nElements);
    }

    @Override
    protected void makeOperation(int value) {
        collection.add(value);
    }
}
