package ua.goit.timonov.enterprise.module_6_2.view.console;

import ua.goit.timonov.enterprise.module_6_2.model.CookedDish;

import java.util.List;
import java.util.Scanner;

import static ua.goit.timonov.enterprise.module_6_2.view.console.PrintToConsole.printEmptyLine;
import static ua.goit.timonov.enterprise.module_6_2.view.console.PrintToConsole.printLine;

/**
 * Created by Alex on 01.08.2016.
 */
public class CookedDishIO {

    public static void outputList(List<CookedDish> dishes) {
        printLine("Current list of cooked dishes: ");
        for (CookedDish cookedDish : dishes) {
            printLine(cookedDish.toString());
            printLine(cookedDish.getOrder().toString());
            printLine(cookedDish.getDish().toString());
            printLine(cookedDish.getCook().toString());
            printEmptyLine();
        }
        printEmptyLine();
    }

    public static int inputId(String fieldName) {
        Scanner sc = new Scanner(System.in);
        printLine("Please input " + fieldName + " id: ");
        return Input.inputInteger(sc);
    }

    public static String inputString(String fieldName) {
        Scanner sc = new Scanner(System.in);
        printLine("Please input " + fieldName + ": ");
        return Input.inputString(sc);
    }
}
