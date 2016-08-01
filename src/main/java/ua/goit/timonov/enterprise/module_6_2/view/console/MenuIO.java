package ua.goit.timonov.enterprise.module_6_2.view.console;

import ua.goit.timonov.enterprise.module_6_2.model.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static ua.goit.timonov.enterprise.module_6_2.view.console.PrintToConsole.printLine;

/**
 * Created by Alex on 31.07.2016.
 */
public class MenuIO {
    public static void outputList(List<Menu> menus) {
        printLine("Current list of menus: ");
        for (Menu menu : menus) {
            printLine(menu.toString());
            DishIO.outputList(menu.getDishes());
        }
    }

    public static void output(String explain, Menu menu) {
        printLine(explain);
        printLine(menu.toString());
        DishIO.outputList(menu.getDishes());
    }

    public static Menu inputMenu() {
        Scanner sc = new Scanner(System.in);
        printLine("Please, input menu's name: ");
        String name = Input.inputString(sc);
        Menu menu = new Menu(0, name, new ArrayList<>());
        return menu;
    }

    public static String inputString(String fieldName) {
        Scanner sc = new Scanner(System.in);
        printLine("Please input menu's " + fieldName + " that you want to find: ");
        return Input.inputString(sc);
    }


}
