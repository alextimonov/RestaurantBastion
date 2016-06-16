package ua.goit.timonov.enterprise.module_1.logic;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for invoke tests for different operations with Collection
 */
public abstract class MultiTest {
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
     * adds different types of testLock to schedule
     */
    public abstract void makeScheduleOfTests();

    /**
     * returns time result of required testLock
     * @param i     index of testLock
     * @return      measured time for required testLock
     */
    public long getResultTime(int i) {
        return tests.get(i).getAverageTime();
    }

    /**
     * returns number of tests
     * @return          testLock number
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
