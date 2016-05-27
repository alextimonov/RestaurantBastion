package ua.goit.timonov.enterprise.module_1;

import java.util.ArrayList;
import java.util.List;

/**
 * Converter measured results to list of Strings for print as a table
 */
public class TableToStrings {
    public static final int N_OF_HYPHENS_IN_TABLE = 137;
    public static final int COLUMN_WIDTH = 16;
    public static final int N_BORDERS = 2;
    public static final char BORDER = '|';
    public static final String SPACE = " ";
    public static final char HYPHEN = '-';
    public static final String EMPTY_STRING = "";

    /* List of string for print as a table */
    private List<String> tableInStrings = new ArrayList<>();

    public List<String> getTableInStrings() {
        return tableInStrings;
    }

    /**
     * prepares list with results as a table
     * @param nElements         number of elements in collection
     * @param allTests          list with multi tests
     */
    public void makeResultsTable(int nElements, List<MultiTest> allTests) {
        printTableHead(nElements);
        for (MultiTest multiTest : allTests) {
            printTestResults(multiTest);
        }
        addEmptyString();
    }

    // adds  string with current test result to list
    private void printTestResults(MultiTest multiTest) {
        tableInStrings.add(printLineResults(multiTest));
        tableInStrings.add(printHyphenLine());
    }

    // adds empty string to list
    private void addEmptyString() {
        tableInStrings.add(EMPTY_STRING);
    }

    // return string with test results for certain type of collection
    private String printLineResults(MultiTest multiTest) {
        StringBuilder sb = new StringBuilder();
        sb.append(BORDER).append(getCollectionNameWithIndent(multiTest));
        for (int i = 0; i < multiTest.getNTests(); i++) {
            sb.append(BORDER).append(String.format("%16d", multiTest.getResultTime(i)));
        }
        sb.append(BORDER);
        return sb.toString();
    }

    // returns name of collection according to width of column
    private String getCollectionNameWithIndent(MultiTest multiTest) {
        String name = SPACE + SPACE + multiTest.getCollectionName();
        for (int i = name.length(); i < COLUMN_WIDTH; i++) {
            name += SPACE;
        }
        return name;
    }

    // adds strings of table head to list
    public void printTableHead(int nElements) {
        tableInStrings.add(printHyphenLine());
        tableInStrings.add(printTableHeader("Results of tests with : " + nElements + " elements"));
        tableInStrings.add(printHyphenLine());
        tableInStrings.add(printColumnHeaders());
        tableInStrings.add(printHyphenLine());
    }

    // prepares string with headers
    private String printColumnHeaders() {
        final StringBuilder sb = new StringBuilder();
        sb.append(BORDER).append("      Type      ");
        sb.append(BORDER).append("    populate    ");
        sb.append(BORDER).append("       add      ");
        sb.append(BORDER).append("       get      ");
        sb.append(BORDER).append("      remove    ");
        sb.append(BORDER).append("    contains    ");
        sb.append(BORDER).append(" iterator.add   ");
        sb.append(BORDER).append("iterator.remove ");
        sb.append(BORDER);
        return sb.toString();
    }

    // creates header of table with given title
    private String printTableHeader(String message) {
        int numberOfSpaces = N_OF_HYPHENS_IN_TABLE - N_BORDERS - message.length() - 1;
        final StringBuilder sb = new StringBuilder();
        sb.append(BORDER).append(SPACE).append(message);
        for (int i = 0; i < numberOfSpaces; i++) {
            sb.append(SPACE);
        }
        sb.append(BORDER);
        return sb.toString();
    }

    // makes string with hyphens
    private String printHyphenLine() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N_OF_HYPHENS_IN_TABLE; i++) {
            sb.append(HYPHEN);
        }
        return sb.toString();
    }
}
