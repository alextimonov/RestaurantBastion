package ua.goit.timonov.enterprise.module_1;


import java.util.Set;

/**
 * Created by Alex on 24.05.2016.
 */
public class ResultsOfSetTest {
    public static final int DEFAULT_VALUE = 0;
    private String type;
    private long timePopulate;
    private long timeAdd;
    private long timeRemove;
    private long timeContains;

    public ResultsOfSetTest(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public long getTimePopulate() {
        return timePopulate;
    }

    public long getTimeAdd() {
        return timeAdd;
    }

    public long getTimeRemove() {
        return timeRemove;
    }

    public long getTimeContains() {
        return timeContains;
    }

    public void runSetTests(Set<Object> testedSet, int nElements) {
        SetTester setTester = new SetTester(testedSet);
        timePopulate = setTester.findTimeOfPopulate(nElements, DEFAULT_VALUE);
        timeAdd = setTester.findTimeOfAdd(nElements, DEFAULT_VALUE);
        timeRemove = setTester.findTimeOfRemove(nElements);
        timeContains = setTester.findTimeOfContains(nElements, DEFAULT_VALUE);
    }
}
