package ua.goit.timonov.enterprise.module_1.controller;

/**
 * App for measure time of different operations with collections
 * (ArrayList, LinkedList, HashSet, TreeSet). SemaphoreMain class executes tests for different number of elements
 */
public class Main {

    public static final int SET_10K = 10000;
    public static final int SET_100K = 100000;
    public static final int SET_1000K = 1000000;

    public static void main(String[] args) {
        TestExecutor executor = new TestExecutor();
        executor.runTest(SET_10K);
        executor.runTest(SET_100K);
        executor.runTest(SET_1000K);
        executor.printTableToConsole();
        executor.printTableToFile();
    }
}
