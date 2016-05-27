package ua.goit.timonov.enterprise.module_1;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Alex on 24.05.2016.
 */
public class MultiTestSet<T> {
    private Set<T> collection;
    int nElements;
    private List<TestSet> tests = new ArrayList<>();

    public MultiTestSet(Set<T> collection, int nElements) {
        this.collection = collection;
        this.nElements = nElements;
    }

    public void makeSetTests(Set<T> testedSet, int nElements) {
        makeScheduleOfTests();
        tests.forEach(TestSet::makeTest);
    }

    private void makeScheduleOfTests() {
        tests.add(new TestSetPopulate(collection, nElements));
        tests.add(new TestSetAdd(collection, nElements));
        tests.add(new TestSetRemove(collection, nElements));
        tests.add(new TestSetContains(collection, nElements));
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
