package ua.goit.timonov.enterprise.module_1;

import java.util.List;

/**
 * Created by Alex on 24.05.2016.
 */
public class ResultsOfListTest {
    public static final int DEFAULT_VALUE = 0;
    private String type;
    private long timePopulate;
    private long timeAdd;
    private long timeGet;
    private long timeRemove;
    private long timeContains;
    private long timeIteratorAdd;
    private long timeIteratorRemove;

    public ResultsOfListTest(String type) {
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

    public long getTimeGet() {
        return timeGet;
    }

    public long getTimeRemove() {
        return timeRemove;
    }

    public long getTimeContains() {
        return timeContains;
    }

    public long getTimeIteratorAdd() {
        return timeIteratorAdd;
    }

    public long getTimeIteratorRemove() {
        return timeIteratorRemove;
    }
    public void runListTests(List<Object> testedList, int nElements) {
        ListTester listTester = new ListTester(testedList);
        timePopulate = listTester.findTimeOfPopulate(nElements, DEFAULT_VALUE);
        timeAdd = listTester.findTimeOfAdd(nElements, DEFAULT_VALUE);
        timeGet = listTester.findTimeOfGet(nElements);
        timeRemove = listTester.findTimeOfRemove(nElements);
        timeContains = listTester.findTimeOfContains(nElements, DEFAULT_VALUE);
        timeIteratorAdd = listTester.findTimeOfListIteratorAdd(nElements, DEFAULT_VALUE);
        timeIteratorRemove = listTester.findTimeOfIteratorRemove(nElements);
    }
}
