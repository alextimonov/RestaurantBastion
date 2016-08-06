package ua.goit.timonov.enterprise.module_6_2.view.console;

import java.util.List;

/**
 * Provides printing messages to console
 */
public class ConsolePrinter {

    /**
     * prints empty line to console
     */
    public static void printEmptyLine() {
        System.out.println();
    }

    /**
     * prints string message to console
     * @param line       string to print
     */
    public static void printLine(String line) {
        System.out.println(line);
    }

    /**
     * prints given list of strings to console
     * @param lines         List of strings to print
     */
    public static void printList(List<String> lines) {
        lines.forEach(System.out::println);
    }
}
