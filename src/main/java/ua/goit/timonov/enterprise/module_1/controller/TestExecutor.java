package ua.goit.timonov.enterprise.module_1.controller;

import ua.goit.timonov.enterprise.module_1.logic.MultiTest;
import ua.goit.timonov.enterprise.module_1.logic.MultiTestSet;
import ua.goit.timonov.enterprise.module_1.view.TableToStrings;
import ua.goit.timonov.enterprise.module_1.logic.MultiTestList;
import ua.goit.timonov.enterprise.module_1.view.Printer;

import javax.swing.*;
import java.util.*;

/**
 * Control execution class - starts measuring and output
 */
public class TestExecutor <T> {

    /* tested collections */
    private List<T> arrayList = new ArrayList<>();
    private List<T> linkedList = new LinkedList<>();
    private Set<T> hashSet = new HashSet<>();
    private Set<T> treeSet = new TreeSet<>();

    /* list of multi tests for tested collections */
    private List<MultiTest> allTests;

    /* converter of results to list of Strings */
    private TableToStrings tableToStrings = new TableToStrings();

    /**
     * runs testLock execution for collection
     * @param nElements     number of elements in the collection
     */
    public void runTest(int nElements) {
        createListOfMultiTests(nElements);
        for (MultiTest multiTest : allTests) {
            multiTest.makeScheduleOfTests();
            multiTest.runTests();
        }
        tableToStrings.makeResultsTable(nElements, allTests);
    }

    // creates list of tests for collections
    private void createListOfMultiTests(int nElements) {
        allTests = new ArrayList<>();
        allTests.add(new MultiTestList<>(arrayList, nElements));
        allTests.add(new MultiTestList<>(linkedList, nElements));
        allTests.add(new MultiTestSet<>(hashSet, nElements));
        allTests.add(new MultiTestSet<>(treeSet, nElements));
    }

    /**
     * invokes method for print string list with results to console
     */
    public void printTableToConsole() {
        Printer.printToConsole(tableToStrings.getTableInStrings());
    }

    /**
     * invokes method for print results to file
     */
    public void printTableToFile() {
        Printer.printToFile(tableToStrings.getTableInStrings());
    }

    public void printToFrameTable(JTable table) {
        String[] columnNames = {"Type", "populate", "add", "get", "delete", "contains", "iterator.add", "iterator.delete"};
        for (int j = 0; j < table.getColumnCount(); j++) {
            table.setValueAt(columnNames[j], 0, j);
        }
        int row = 0;
        for (MultiTest multiTest : allTests) {
            table.setValueAt(multiTest.getCollectionName(), row + 1, 0);
            for (int j = 0; j < multiTest.getNTests(); j++) {
                table.setValueAt(multiTest.getResultTime(j), row + 1, j + 1);
            }
            row++;
        }
    }
}