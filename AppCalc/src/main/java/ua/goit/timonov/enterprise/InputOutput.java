package ua.goit.timonov.enterprise;

import java.util.Scanner;

/**
 * Class to input string expression from console and output the result of calculation or message about wrong input data format
 */
public class InputOutput {

    /**
     * returns inputted string
     * @return      inputted string
     */
    public String inputStringFromConsole() {
        System.out.println("Input number expression (operations +, -, *, /, LN(x), SQRT(x), x!  are supported). " +
                "Input \"Q\" or \"q\" to quit ");
        Scanner scan = new Scanner(System.in);
        String inputString = scan.next();
        return inputString.toLowerCase();
    }

    /**
     * outputs inputted string expression and the result od its execution
     * @param input     string with inputted expression
     * @param result    string with result
     */
    public void printToConsole(String input, String result) {
        System.out.println("Result is: " + input + " = " + result);
    }

    /**
     * outputs message about wrong data format of inputted expression
     * @param input         string with inputted expression
     * @param message       string of caught exception
     */
    public void printErrorToConsole(String input, String message) {
        System.out.println("Wrong input format: " + input + ", error: " + message);
    }
}