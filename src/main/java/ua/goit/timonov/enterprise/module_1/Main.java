package ua.goit.timonov.enterprise.module_1;

/**
 * Created by Alex on 22.05.2016.
 */
public class Main {

    public static final int SET_10K = 10000;
    public static final int SET_100K = 100000;
    public static final int SET_1000K = 1000000;

    public static void main(String[] args) {

        TestExecutor executor = new TestExecutor(SET_10K);
        executor.runListTestWithNumber();
        executor.runSetTestWithNumber();

        executor = new TestExecutor(SET_100K);
        executor.runListTestWithNumber();
        executor.runSetTestWithNumber();

        executor = new TestExecutor(SET_1000K);
        executor.runListTestWithNumber();
        executor.runSetTestWithNumber();
    }

}
