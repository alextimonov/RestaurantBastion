package ua.goit.timonov.enterprise.module_1;

/**
 * Created by Alex on 22.05.2016.
 */
public class Main {

    public static final int SET_10K = 10000;
    public static final int SET_100K = 100000;
    public static final int SET_1000K = 1000000;

    public static void main(String[] args) {
        TestExecutor executor = new TestExecutor();
        executor.runListTest(SET_10K);
        executor.runSetTest(SET_10K);

        executor.runListTest(SET_100K);
        executor.runSetTest(SET_100K);

        executor.runListTest(SET_1000K);
        executor.runSetTest(SET_1000K);

        executor.printTableToConsole();
        executor.printTableToFile();
    }
}
