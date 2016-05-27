package ua.goit.timonov.enterprise.module_1;

import java.util.List;

/**
 * Implementation of operation contains(value) for list
 */
public class TestListContains extends TestList {
    public <T> TestListContains(List<T> collection, int nElements) {
        super(collection, nElements);
    }

    @Override
    public void makeOperation(int index, int value) {
        collection.contains(value);
    }
}
