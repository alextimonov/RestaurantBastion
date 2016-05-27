package ua.goit.timonov.enterprise.module_1;

import java.util.Set;

/**
 * Class that invokes tests for different operations with Set (can be HashSet or TreeSet)
 */
public class MultiTestSet<T> extends MultiTest {
    /* tested Set */
    private Set<T> collection;

    public MultiTestSet(Set<T> collection, int nElements) {
        super(nElements);
        this.collection = collection;
    }

    // adds different types of test to schedule
    protected void makeScheduleOfTests() {
        tests.add(new TestSetPopulate(collection, nElements));
        tests.add(new TestSetAdd(collection, nElements));
        tests.add(new EmptyTest());
        tests.add(new TestSetRemove(collection, nElements));
        tests.add(new TestSetContains(collection, nElements));
        tests.add(new EmptyTest());
        tests.add(new EmptyTest());
    }

    /**
     * returns name of tested collection
     * @return          String with name of tested collection
     */
    public String getCollectionName() {
        return collection.getClass().getSimpleName();
    }
}
