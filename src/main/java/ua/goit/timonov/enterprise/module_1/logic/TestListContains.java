package ua.goit.timonov.enterprise.module_1.logic;

import java.util.List;

/**
 * Implementation of operation contains(value) for list
 */
public class TestListContains extends TestList {
    public TestListContains(List collection, int nElements) {
        super(collection, nElements);
    }

    @Override
    public void makeOperation(int index, int value) {
        collection.contains(value);
    }
}
