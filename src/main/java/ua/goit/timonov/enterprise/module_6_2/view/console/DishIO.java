package ua.goit.timonov.enterprise.module_6_2.view.console;

import ua.goit.timonov.enterprise.module_6_2.model.Dish;

import java.util.List;
import java.util.Scanner;

import static ua.goit.timonov.enterprise.module_6_2.view.console.PrintToConsole.printEmptyLine;
import static ua.goit.timonov.enterprise.module_6_2.view.console.PrintToConsole.printLine;

/**
 * Created by Alex on 31.07.2016.
 */
public class DishIO {

    public static void outputList(List<Dish> dishes) {
        printLine("Current list of dishes: ");
        for (Dish dish : dishes) {
            printLine(dish.toString());
        }
        printEmptyLine();
    }

    public static void output(String explain, Dish dish) {
        printLine(explain);
        printLine(dish.toString());
    }

    public static Dish inputDish() {
        Scanner sc = new Scanner(System.in);
        Dish dish = new Dish();
        printLine("Please, input new dish's data: ");

        printLine("Input dish's name: ");
        dish.setName(Input.inputString(sc));

        printLine("Input dish's description: ");
        dish.setDescription(Input.inputString(sc));

        printLine("Input dish's cost: ");
        dish.setCost(Input.inputFloat(sc));

        printLine("Input dish's weight: ");
        dish.setWeight(Input.inputInteger(sc));

        return dish;
    }

    public static String inputString(String fieldName) {
        Scanner sc = new Scanner(System.in);
        printLine("Please input dish's " + fieldName + " that you want to find: ");
        return Input.inputString(sc);
    }
}
