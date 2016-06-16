package ua.goit.timonov.enterprise.module_1.logic;

/**
 * Interface with methods for each testLock
 */
public interface Test {
    /* number of testLock repetitions */
    int REPETITIONS = 10;
    /**
     * @return      measured time for this testLock
     */
    long getAverageTime();

    /**
     * makes testLock for given number of repetitions and finds average time for this testLock
     */
    void makeTest();

    /**
     * makes testLock once and measures elapsed time
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
