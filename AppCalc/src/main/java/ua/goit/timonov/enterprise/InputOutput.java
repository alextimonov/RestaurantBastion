package ua.goit.timonov.enterprise;

import java.util.Scanner;

/**
 * Created by Alex on 30.06.2016.
 */
public class InputOutput {

    String inputStringFromConsole() {
        System.out.println("Input expression: ");
        Scanner scan = new Scanner(System.in);
        String inputString = scan.next();
        scan.close();
        return inputString;
    }

    public void printToConsole(String input, String result) {
        System.out.println(input + " = " + result);
    }
}