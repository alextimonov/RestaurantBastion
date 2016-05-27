package ua.goit.timonov.enterprise.module_1.logic;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for invoke tests for different operations with Collection
 */
public abstract class MultiTest<T> {
    /* number of elements in collection */
    protected int nElements;
    /* List with tests and their time results */
    protected List<Test> tests = new ArrayList<>();

    public MultiTest(int nElements) {
        this.nElements = nElements;
    }

    /**
     * runs tests
     */
    public void runTests() {
        for (Test test : tests) test.makeTest();
    }

    /**
     * adds different types of test to schedule
     */
    public abstract void makeScheduleOfTests();

    /**
     * returns time result of required test
     * @param i     index of test
     * @return      measured time for required test
     */
    public long getResultTime(int i) {
        return tests.get(i).getAverageTime();
    }

    /**
     * returns number of tests
     * @return          test number
     */
    public int getNTests() {
        return tests.size();
    }

    /**
     * returns name of tested collection
     * @return          String with name of tested collection
     */
    public abstract String getCollectionName();
}
