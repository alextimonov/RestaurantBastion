package ua.goit.timonov.enterprise.module_6_2.view.console;

import ua.goit.timonov.enterprise.module_6_2.model.*;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static ua.goit.timonov.enterprise.module_6_2.view.console.ConsolePrinter.printEmptyLine;
import static ua.goit.timonov.enterprise.module_6_2.view.console.ConsolePrinter.printLine;
import static ua.goit.timonov.enterprise.module_6_2.view.console.ConsolePrinter.printList;

/**
 * Created by Alex on 03.08.2016.
 */
public class ConsoleIO {

    public static final String PLEASE_INPUT = "Please input ";
    public static final String POSSESIVE = "'s ";
    public static final String COLON = ": ";

    public static String inputString(String itemName, String fieldName) {
        Scanner sc = new Scanner(System.in);
        printLine(PLEASE_INPUT + itemName + POSSESIVE + fieldName + COLON);
        return ConsoleInput.inputString(sc);
    }

    public static Integer inputInteger(String itemName, String fieldName) {
        Scanner sc = new Scanner(System.in);
        printLine(PLEASE_INPUT + itemName + POSSESIVE + fieldName + COLON);
        return ConsoleInput.inputInteger(sc);
    }

    public static void outputItem(String explain, String item) {
        printLine(explain);
        printLine(item);
    }

    public static void outputList(String itemsName, List<String> items) {
        printLine("\nCurrent list of " + itemsName + COLON);
        printList(items);
    }

    public static void outputDishes(List<Dish> dishes) {
        printLine("Current list of dishes: ");
        for (Dish dish : dishes) {
            printLine(dish.toString());
        }
        printEmptyLine();
    }

    public static void outputMenus(List<Menu> menus) {
        printLine("Current list of menus: ");
        for (Menu menu : menus) {
            printLine(menu.toString());
            outputDishes(menu.getDishes());
        }
        printEmptyLine();
    }

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

    public static void outputOrders(String orderType, List<Order> orders) {
        printLine("Current list of " + orderType + " orders: ");
        for (Order order : orders) {
            printLine(order.toString());
            ConsoleIO.outputDishes(order.getDishes());
        }
        printEmptyLine();
    }

    public static void outputIngredients(List<Ingredient> ingredients) {
        printLine("Current list of ingredients: ");
        for (Ingredient ingredient: ingredients) {
            printLine(ingredient.toString());
        }
        printEmptyLine();

    }

    public static Employee inputEmployee() {
        Employee employee = new Employee();
        Scanner sc = new Scanner(System.in);
        printLine("Please, input new employee's data: ");

        printLine("Input employee's surname: ");
        employee.setSurname(ConsoleInput.inputString(sc));

        printLine("Input employee's name: ");
        employee.setName(ConsoleInput.inputString(sc));

        printLine("Input employee's birthday: ");
        Date date = ConsoleInput.inputDate(sc);
        employee.setBirthday(date);

        printLine("Input employee's position: ");
        sc = new Scanner(System.in);
        employee.setPosition(ConsoleInput.inputString(sc));

        printLine("Input employee's salary: ");
        employee.setSalary(ConsoleInput.inputFloat(sc));

        return employee;
    }

    public static Dish inputDish() {
        Scanner sc = new Scanner(System.in);
        Dish dish = new Dish();
        printLine("Please, input new dish's data: ");

        printLine("Input dish's name: ");
        dish.setName(ConsoleInput.inputString(sc));

        printLine("Input dish's description: ");
        dish.setDescription(ConsoleInput.inputString(sc));

        printLine("Input dish's cost: ");
        dish.setCost(ConsoleInput.inputFloat(sc));

        printLine("Input dish's weight: ");
        dish.setWeight(ConsoleInput.inputInteger(sc));

        return dish;
    }

    public static Menu inputMenu() {
        Scanner sc = new Scanner(System.in);
        printLine("Please, input menu's name: ");
        String name = ConsoleInput.inputString(sc);
        Menu menu = new Menu(name);
        return menu;
    }

    public static Order inputOrder() {
        Scanner sc = new Scanner(System.in);
        printLine("Please, input date of order: ");
        Date date = ConsoleInput.inputDate(sc);
        printLine("Please, input table number: ");
        int nTable = ConsoleInput.inputInteger(sc);
        printLine("Please, input waiter's id: ");
        int waiterId = ConsoleInput.inputInteger(sc);
        Order order = new Order(waiterId, nTable, date);
        return order;
    }

    public static Ingredient inputIngredient() {
        Scanner sc = new Scanner(System.in);
        Ingredient ingredient = new Ingredient();
        printLine("Please, input new ingredient's data: ");

        printLine("Input ingredient's name: ");
        ingredient.setName(ConsoleInput.inputString(sc));

        printLine("Input ingredient's start amount: ");
        ingredient.setAmount(ConsoleInput.inputInteger(sc));

        return ingredient;
    }

}
