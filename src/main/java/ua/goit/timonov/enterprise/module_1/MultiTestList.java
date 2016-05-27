package ua.goit.timonov.enterprise.module_1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 24.05.2016.
 */
public class MultiTestList<T> {
//    private String type;
    private List<T> collection;
    int nElements;
    private List<TestList> tests = new ArrayList<>();

    public MultiTestList(List<T> collection, int nElements) {
        this.collection = collection;
        this.nElements = nElements;
    }

    public void makeListTests(List<Object> testedList, int nElements) {
        makeScheduleOfTests();
        tests.forEach(TestList::makeTest);
    }

    private void makeScheduleOfTests() {
        tests.add(new TestListPopulate(collection, nElements));
        tests.add(new TestListAdd(collection, nElements));
        tests.add(new TestListGet(collection, nElements));
        tests.add(new TestListRemove(collection, nElements));
        tests.add(new TestListContains(collection, nElements));
        tests.add(new TestIteratorAdd(collection, nElements));
        tests.add(new TestIteratorRemove(collection, nElements));
    }

    public long getResultTime(int i) {
        return tests.get(i).getElapsedTime();
    }

    public int getNTests() {
        return tests.size();
    }

    public String getCollectionName() {
        return collection.getClass().getSimpleName();
    }
}