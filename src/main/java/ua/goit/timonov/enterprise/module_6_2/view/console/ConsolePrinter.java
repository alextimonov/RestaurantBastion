package ua.goit.timonov.enterprise.module_6_2.view.console;

import java.util.List;

/**
 * Created by Alex on 31.07.2016.
 */
public class ConsolePrinter {

    public static void printEmptyLine() {
        System.out.println();
    }

    public static void printLine(String line) {
        System.out.println(line);
    }

    public static void printList(List<String> lines) {
        lines.forEach(System.out::println);
    }
}
