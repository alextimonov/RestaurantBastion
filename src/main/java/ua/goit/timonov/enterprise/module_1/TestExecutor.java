package ua.goit.timonov.enterprise.module_1;

import java.io.IOException;
import java.util.*;

/**
 * Created by Alex on 24.05.2016.
 */
public class TestExecutor {
    private List<Object> arrayList = new ArrayList<>();
    private List<Object> linkedList = new LinkedList<>();
    private MultiTestList arrayListMultiTest;
    private MultiTestList linkedListMultiTest;

    private Set<Object> hashSet = new HashSet<>();
    private Set<Object> treeSet = new TreeSet<>();
    private MultiTestSet hashSetMultiTest;
    private MultiTestSet treeSetMultiTest;

    private TableToStrings tableToStrings = new TableToStrings();

    public void runListTest(int nElements) {
        arrayListMultiTest = new MultiTestList(arrayList, nElements);
        linkedListMultiTest = new MultiTestList(linkedList, nElements);

        arrayListMultiTest.makeListTests(arrayList, nElements);
        linkedListMultiTest.makeListTests(linkedList, nElements);

        tableToStrings.printTableHead(nElements);
        tableToStrings.printListResults(arrayListMultiTest);
        tableToStrings.printListResults(linkedListMultiTest);
    }

    public void runSetTest(int nElements) {
        hashSetMultiTest = new MultiTestSet(hashSet, nElements);
        treeSetMultiTest = new MultiTestSet(treeSet, nElements);

        hashSetMultiTest.makeSetTests(hashSet, nElements);
        treeSetMultiTest.makeSetTests(treeSet, nElements);

        tableToStrings.printListResults(hashSetMultiTest);
        tableToStrings.printListResults(treeSetMultiTest);
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