package ua.goit.timonov.enterprise.module_1;

/**
 * Created by Alex on 24.05.2016.
 */
public class DataTable {
    public static final int N_OF_HYPHENS_IN_TABLE = 101;
    public static final int N_BORDERS = 2;
    public static final char BORDER = '|';
    public static final char SPACE = ' ';
    public static final char HYPHEN = '-';

    public void printTableHead(int nElements) {
        System.out.println(printHyphenLine());
        System.out.println(printTableHeader("Results of tests with : " + nElements + " elements"));
        System.out.println(printHyphenLine());
        System.out.println(printColumnHeaders());
        System.out.println(printHyphenLine());
    }

    private String printColumnHeaders() {
        final StringBuilder sb = new StringBuilder();
        sb.append(BORDER).append("  Type    ");
        sb.append(BORDER).append(" populate ");
        sb.append(BORDER).append("    add   ");
        sb.append(BORDER).append("    get   ");
        sb.append(BORDER).append("   remove ");
        sb.append(BORDER).append(" contains ");
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

    public void printListResults(ResultsOfListTest listResult) {
        System.out.println(printLineListResults(listResult));
        System.out.println(printHyphenLine());
    }

    private String printLineListResults(ResultsOfListTest listResult) {
        StringBuilder sb = new StringBuilder();
        sb.append(BORDER).append(listResult.getType());
        sb.append(BORDER).append(String.format("%10d", listResult.getTimePopulate()));
        sb.append(BORDER).append(String.format("%10d", listResult.getTimeAdd()));
        sb.append(BORDER).append(String.format("%10d", listResult.getTimeGet()));
        sb.append(BORDER).append(String.format("%10d", listResult.getTimeRemove()));
        sb.append(BORDER).append(String.format("%10d", listResult.getTimeContains()));
        sb.append(BORDER).append(String.format("%16d", listResult.getTimeIteratorAdd()));
        sb.append(BORDER).append(String.format("%16d", listResult.getTimeIteratorRemove()));
        sb.append(BORDER);
        return sb.toString();
    }

    public void printListResults(ResultsOfSetTest setResult) {
        System.out.println(printLineSetResults(setResult));
        System.out.println(printHyphenLine());
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
}
