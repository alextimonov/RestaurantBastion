package ua.goit.timonov.enterprise.module_1;

import java.util.List;

/**
 * Created by Alex on 27.05.2016.
 */
public class TestListContains extends TestList {
    public <T> TestListContains(List<T> collection, int nElements) {
        super(collection, nElements);
    }

    @Override
    protected void makeOperation(int index, int value) {
        collection.contains(value);
    }
}
