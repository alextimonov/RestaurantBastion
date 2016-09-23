package ua.goit.timonov.enterprise.module_1.logic;

/**
 * Class with "empty testLock" as there are no operations get(), iterator.add(), iterator.remove for Sets
 */
public class EmptyTest implements Test {
    private long elapsedTime = 0;

    public EmptyTest() {
    }

    public long getAverageTime() {
        return elapsedTime;
    }

    @Override
    public void makeTest() {
    }

    @Override
    public long fixTimeOfOperation() {
        return 0;
    }

    @Override
    public void makeOperation(int index, int value) {
    }
}
