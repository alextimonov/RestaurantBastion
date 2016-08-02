package ua.goit.timonov.enterprise.module_6_2.view.console;

import ua.goit.timonov.enterprise.module_6_2.model.Ingredient;

import java.util.List;
import java.util.Scanner;

import static ua.goit.timonov.enterprise.module_6_2.view.console.PrintToConsole.printEmptyLine;
import static ua.goit.timonov.enterprise.module_6_2.view.console.PrintToConsole.printLine;

/**
 * Created by Alex on 02.08.2016.
 */
public class StorageIO {

    public static void outputList(List<Ingredient> ingredients) {
        printLine("Current list of ingredients: ");
        for (Ingredient ingredient: ingredients) {
            printLine(ingredient.toString());
        }
        printEmptyLine();

    }

    public static void output(String explain, Ingredient ingredient) {
        printLine(explain);
        printLine(ingredient.toString());
    }

    public static Ingredient inputIngredient() {
        Scanner sc = new Scanner(System.in);
        Ingredient ingredient = new Ingredient();
        printLine("Please, input new ingredient's data: ");

        printLine("Input ingredient's name: ");
        ingredient.setName(Input.inputString(sc));

        printLine("Input ingredient's start amount: ");
        ingredient.setAmount(Input.inputInteger(sc));

        return ingredient;
    }

    public static String inputString(String fieldName) {
        Scanner sc = new Scanner(System.in);
        printLine("Please input ingredient's " + fieldName + " that you want to find: ");
        return Input.inputString(sc);
    }

    public static int inputInteger(String fieldName) {
        Scanner sc = new Scanner(System.in);
        printLine("Please input ingredient's " + fieldName + " that you want to find: ");
        return Input.inputInteger(sc);
    }
}
