package ua.goit.timonov.enterprise.module_1.logic;

import java.util.List;

/**
 * Class that invokes tests for different operations with List (can be ArrayList or LinkedList)
 */
public class MultiTestList<T> extends MultiTest {
    /* tested list */
    private List<T> collection;

    public MultiTestList(List<T> collection, int nElements) {
        super(nElements);
        this.collection = collection;
    }

    // adds different types of test to schedule
    @Override
    public void makeScheduleOfTests() {
        tests.add(new TestListPopulate(collection, nElements));
        tests.add(new TestListAdd(collection, nElements));
        tests.add(new TestListGet(collection, nElements));
        tests.add(new TestListRemove(collection, nElements));
        tests.add(new TestListContains(collection, nElements));
        tests.add(new TestIteratorAdd(collection, nElements));
        tests.add(new TestIteratorRemove(collection, nElements));
    }

    /**
     * returns name of tested collection
     * @return          String with name of tested collection
     */
    public String getCollectionName() {
        return collection.getClass().getSimpleName();
    }
}