package ua.goit.timonov.enterprise.module_1;

import java.util.List;

/**
 * Implementation of operation remove(index) for list
 */
public class TestListRemove<T> extends TestList {
    public <T> TestListRemove(List<T> collection, int nElements) {
        super(collection, nElements);
    }

    @Override
    public void makeOperation(int index, int value) {
        collection.remove(index);
    }
}
