package ua.goit.timonov.enterprise.module_1;

import java.util.Set;

/**
 * Created by Alex on 27.05.2016.
 */
public class TestSetContains extends TestSet {

    public TestSetContains(Set collection, int nElements) {
        super(collection, nElements);
    }

    @Override
    protected void makeOperation(int value) {
        collection.contains(value);
    }
}
