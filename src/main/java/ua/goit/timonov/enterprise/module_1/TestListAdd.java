package ua.goit.timonov.enterprise.module_1;

import java.util.List;

/**
 * Implementation of operation add(index, value) for list
 */
public class TestListAdd extends TestList {

    public <T> TestListAdd(List<T> collection, int nElements) {
        super(collection, nElements);
    }

    @Override
    public void makeOperation(int index, int value) {
        collection.add(index, value);
    }
}
