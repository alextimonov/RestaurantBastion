package ua.goit.timonov.enterprise.module_1;

/**
 * Interface with methods for each test
 */
public interface Test {
    /* number of test repetitions */
    int REPETITIONS = 100;
    /**
     * @return      measured time for this test
     */
    long getAverageTime();

    /**
     * makes test for given number of repetitions and finds average time for this test
     */
    void makeTest();

    /**
     * makes test once and measures elapsed time
     * @return          elapsed time for current repetition
     */
    long fixTimeOfOperation();

    /**
     * makes once only one type of operation
     * @param index     index of element to add, remove etc.
     * @param value     value of added element, element to find etc.
     */
    void makeOperation(int index, int value);
}
