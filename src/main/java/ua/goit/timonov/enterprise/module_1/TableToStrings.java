package ua.goit.timonov.enterprise.module_1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 24.05.2016.
 */
public class TableToStrings {
    public static final int N_OF_HYPHENS_IN_TABLE = 137;
    public static final int N_BORDERS = 2;
    public static final char BORDER = '|';
    public static final char SPACE = ' ';
    public static final char HYPHEN = '-';
    public static final String EMPTY_STRING = "";
    public static final int COLUMN_WIDTH = 16;

    private List<String> tableInStrings = new ArrayList<>();

    public List<String> getTableInStrings() {
        return tableInStrings;
    }

    public void printTableHead(int nElements) {
        tableInStrings.add(printHyphenLine());
        tableInStrings.add(printTableHeader("Results of tests with : " + nElements + " elements"));
        tableInStrings.add(printHyphenLine());
        tableInStrings.add(printColumnHeaders());
        tableInStrings.add(printHyphenLine());
    }

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

    /**
     * creates header of table with given title
     * @param message       given title of table
     * @return              string with header of table
     */
    public String printTableHeader(String message) {
        int numberOfSpaces = N_OF_HYPHENS_IN_TABLE - N_BORDERS - message.length() - 1;
        final StringBuilder sb = new StringBuilder();
        sb.append(BORDER).append(SPACE).append(message);
        for (int i = 0; i < numberOfSpaces; i++) {
            sb.append(SPACE);
        }
        sb.append(BORDER);
        return sb.toString();
    }

    public String printHyphenLine() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N_OF_HYPHENS_IN_TABLE; i++) {
            sb.append(HYPHEN);
        }
        return sb.toString();
    }

    public void printListResults(MultiTestList multiTestList) {
        tableInStrings.add(printLineListResults(multiTestList));
        tableInStrings.add(printHyphenLine());
    }

    private String printLineListResults(MultiTestList multiTestList) {
        StringBuilder sb = new StringBuilder();
        sb.append(BORDER).append(getCollectionNameWithIndent(multiTestList));
        for (int i = 0; i < multiTestList.getNTests(); i++) {
            sb.append(BORDER).append(String.format("%16d", multiTestList.getResultTime(i)));
        }
        sb.append(BORDER);
        return sb.toString();
    }

    private String getCollectionNameWithIndent(MultiTestList multiTestList) {
        String name = multiTestList.getCollectionName();
        for (int i = name.length(); i < COLUMN_WIDTH; i++) {
            name += " ";
        }
        return name;
    }

    public void printListResults(ResultsOfSetTest setResult) {
        tableInStrings.add(printLineSetResults(setResult));
        tableInStrings.add(printHyphenLine());
    }

    private String printLineSetResults(ResultsOfSetTest setResult) {
        StringBuilder sb = new StringBuilder();
        sb.append(BORDER).append(setResult.getType());
        sb.append(BORDER).append(String.format("%10d", setResult.getTimePopulate()));
        sb.append(BORDER).append(String.format("%10d", setResult.getTimeAdd()));
        sb.append(BORDER).append("          ");
        sb.append(BORDER).append(String.format("%10d", setResult.getTimeRemove()));
        sb.append(BORDER).append(String.format("%10d", setResult.getTimeContains()));
        sb.append(BORDER).append("                ");
        sb.append(BORDER).append("                ");
        sb.append(BORDER);
        return sb.toString();
    }

    public void addEmptyString() {
        tableInStrings.add(EMPTY_STRING);
    }
}
