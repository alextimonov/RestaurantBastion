package ua.goit.timonov.enterprise.module_1;

import java.util.*;

/**
 * Created by Alex on 24.05.2016.
 */
public class TestExecutor {
    private int nElements;
    private List<Object> arrayList = new ArrayList<Object>();
    private List<Object> linkedList = new LinkedList<Object>();
    private ResultsOfListTest arrayListResult = new ResultsOfListTest("ArrayList ");
    private ResultsOfListTest linkedListResult = new ResultsOfListTest("LinkedList");

    private Set<Object> hashSet = new HashSet<Object>();
    private Set<Object> treeSet = new TreeSet<Object>();
    private ResultsOfSetTest hashSetResult = new ResultsOfSetTest(" HashSet  ");
    private ResultsOfSetTest treeSetResult = new ResultsOfSetTest(" TreeSet  ");

    private DataTable dataTable = new DataTable();

    public TestExecutor(int nElements) {
        this.nElements = nElements;
    }

    public void runListTestWithNumber() {
        dataTable.printTableHead(nElements);
        arrayListResult.runListTests(arrayList, nElements);
        dataTable.printListResults(arrayListResult);
        linkedListResult.runListTests(linkedList, nElements);
        dataTable.printListResults(linkedListResult);
    }

    public void runSetTestWithNumber() {
        hashSetResult.runSetTests(hashSet, nElements);
        dataTable.printListResults(hashSetResult);
        treeSetResult.runSetTests(treeSet, nElements);
        dataTable.printListResults(treeSetResult);
    }
}