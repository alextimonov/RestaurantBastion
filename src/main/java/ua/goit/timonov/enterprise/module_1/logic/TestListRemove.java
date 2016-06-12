package ua.goit.timonov.enterprise.module_1.logic;

import java.util.List;

/**
 * Implementation of operation remove(index) for list
 */
public class TestListRemove extends TestList {
    public TestListRemove(List collection, int nElements) {
        super(collection, nElements);
    }

    @Override
    public void makeOperation(int index, int value) {
        collection.remove(index);
    }
}
