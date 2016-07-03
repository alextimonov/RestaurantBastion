package ua.goit.timonov.enterprise;

import java.util.Scanner;

/**
 * Created by Alex on 30.06.2016.
 */
public class InputOutput {

    String inputStringFromConsole() {
        System.out.println("Input number expression (operations +, -, *, / are permitted). Input \"Q\" or \"q\" to quit ");
        Scanner scan = new Scanner(System.in);
        String inputString = scan.next();
        return inputString;
    }

    public void printToConsole(String input, String result) {
        System.out.println("Result is: " + input + " = " + result);
    }

    public void printErrorToConsole(String input, String message) {
        System.out.println("Wrong input format: " + input + ", error: " + message);
    }
}