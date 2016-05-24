package ua.goit.timonov.enterprise.module_1;

import java.io.IOException;
import java.util.*;

/**
 * Created by Alex on 24.05.2016.
 */
public class TestExecutor {
//    private int nElements;
    private List<Object> arrayList = new ArrayList<Object>();
    private List<Object> linkedList = new LinkedList<Object>();
    private ResultsOfListTest arrayListResult = new ResultsOfListTest("ArrayList ");
    private ResultsOfListTest linkedListResult = new ResultsOfListTest("LinkedList");

    private Set<Object> hashSet = new HashSet<>();
    private Set<Object> treeSet = new TreeSet<Object>();
    private ResultsOfSetTest hashSetResult = new ResultsOfSetTest(" HashSet  ");
    private ResultsOfSetTest treeSetResult = new ResultsOfSetTest(" TreeSet  ");

    private TableToStrings tableToStrings = new TableToStrings();

    public TestExecutor() {
    }

    public void runListTest(int nElements) {
        arrayListResult.runListTests(arrayList, nElements);
        linkedListResult.runListTests(linkedList, nElements);

        tableToStrings.printTableHead(nElements);
        tableToStrings.printListResults(arrayListResult);
        tableToStrings.printListResults(linkedListResult);
    }

    public void runSetTest(int nElements) {
        hashSetResult.runSetTests(hashSet, nElements);
        treeSetResult.runSetTests(treeSet, nElements);

        tableToStrings.printListResults(hashSetResult);
        tableToStrings.printListResults(treeSetResult);
        tableToStrings.addEmptyString();
    }

    public void printTableToConsole() {
        Printer.printToConsole(tableToStrings.getTableInStrings());
    }

    public void printTableToFile() {
        try {
            Printer.printToFile(tableToStrings.getTableInStrings());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}