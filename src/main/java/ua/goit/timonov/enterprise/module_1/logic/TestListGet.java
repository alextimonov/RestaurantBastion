package ua.goit.timonov.enterprise.module_1.logic;

import java.util.List;

/**
 * Implementation of operation get(index) for list
 */
public class TestListGet extends TestList {
    public <T> TestListGet(List<T> collection, int nElements) {
        super(collection, nElements);
    }

    @Override
    public void makeOperation(int index, int value) {
        collection.get(index);
    }
}
