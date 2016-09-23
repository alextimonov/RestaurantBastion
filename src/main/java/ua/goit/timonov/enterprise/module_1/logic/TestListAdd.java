package ua.goit.timonov.enterprise.module_1.logic;

import java.util.List;

/**
 * Implementation of operation add(index, value) for list
 */
public class TestListAdd extends TestList {

    public TestListAdd(List collection, int nElements) {
        super(collection, nElements);
    }

    @Override
    public void makeOperation(int index, int value) {
        collection.add(index, value);
    }
}
