package ua.goit.timonov.enterprise;

import java.util.Scanner;

/**
 * Class to input string expressionType from console and output the result of calculation or message about wrong input data format
 */
public class InputOutput {

    public InputOutput() {
    }

    /**
     * returns inputted string
     * @return      inputted string
     */
    public String inputStringFromConsole() {
        System.out.println("Input number expressionType (operations +, -, *, /, LN(x), SQRT(x), x!  are supported). " +
                "Input \"Q\" or \"q\" to quit ");
        Scanner scan = new Scanner(System.in);
        String inputString = scan.next();
        return inputString.toLowerCase();
    }

    /**
     * outputs inputted string expressionType and the result od its execution
     * @param input     string with inputted expressionType
     * @param result    string with result
     */
    public void printToConsole(String input, String result) {
        System.out.println("Result is: " + input + " = " + result);
    }

    /**
     * outputs message about wrong data format of inputted expressionType
     * @param input         string with inputted expressionType
     * @param message       string of caught exception
     */
    public void printErrorToConsole(String input, String message) {
        System.out.println("Wrong input format: " + input + ", error: " + message);
    }
}