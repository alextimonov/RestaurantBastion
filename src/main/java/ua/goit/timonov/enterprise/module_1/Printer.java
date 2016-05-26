package ua.goit.timonov.enterprise.module_1;

import java.io.*;
import java.util.List;

/**
 * Created by Alex on 24.05.2016.
 */
public class Printer {

    public static final String PATHNAME = "resources\\txt\\module_1\\test_results.txt";

    public static void printToConsole(List<String> table) {
        for (String line : table) {
            System.out.println(line);
        }
        System.out.println();
    }

    public static void printToFile(List<String> table) throws IOException {
        String newLine = System.getProperty("line.separator");
        try (FileWriter writer = new FileWriter(PATHNAME)) {
            for (String line : table) {
                writer.write(line);
                writer.write(newLine);
            }
            writer.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
