package ua.goit.timonov.enterprise.module_6_2.view.console;

import ua.goit.timonov.enterprise.module_6_2.model.*;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static ua.goit.timonov.enterprise.module_6_2.view.console.ConsolePrinter.printEmptyLine;
import static ua.goit.timonov.enterprise.module_6_2.view.console.ConsolePrinter.printLine;

/**
 * Provides input & output operations with console
 */
public class ConsoleIO {

    public static final String PLEASE_INPUT = "Please input ";
    public static final String POSSESIVE = "'s ";
    public static final String COLON = ": ";

    /**
     * provides input of string from console
     * @param itemName      item's (table's) name
     * @param fieldName     field's name
     * @return              inputted string
     */
    public static String inputString(String itemName, String fieldName) {
        printLine(PLEASE_INPUT + itemName + POSSESIVE + fieldName + COLON);
        return ConsoleInput.inputString();
    }

    /**
     * provides input of Integer from console
     * @param itemName      item's (table's) name
     * @param fieldName     field's name
     * @return              inputted integer value
     */
    public static Integer inputInteger(String itemName, String fieldName) {
        Scanner sc = new Scanner(System.in);
        printLine(PLEASE_INPUT + itemName + POSSESIVE + fieldName + COLON);
        return ConsoleInput.inputInteger();
    }

    /**
     * outputs string item and explanation to it
     * @param explain       explanation to outputed item
     * @param item          item to output
     */
    public static void outputItem(String explain, String item) {
        printLine(explain);
        printLine(item);
    }

    /**
     * outputs List of employees
     * @param employees         List of string employees to output
     */
    public static void outputEmployees(List<Employee> employees) {
        printLine("OPERATION SUCCEEDED! Current list of employees: ");
        for (Employee employee : employees) {
            printLine(employee.toString());
        }
    }

    /**
     * outputs List of dishes
     * @param dishes         List of string employees to output
     */
    public static void outputDishes(List<Dish> dishes) {
        printLine("Current list of dishes: ");
        for (Dish dish : dishes) {
            printLine(dish.toString());
        }
        printEmptyLine();
    }

    /**
     * outputs List of menus and dishes in each menu
     * @param menus         List of menus to ouput
     */
    public static void outputMenus(List<Menu> menus) {
        printLine("OPERATION SUCCEEDED! Current list of menus: ");
        for (Menu menu : menus) {
            printLine(menu.toString());
            outputDishes(menu.getDishes());
        }
    }

    /**
     * outputs List of cooked dishes and its orders, cooks and dishes associated with each cooked dish
     * @param dishes
     */
    public static void outputCookedDish(List<CookedDish> dishes) {
        printLine("OPERATION SUCCEEDED! Current list of cooked dishes: ");
        for (CookedDish cookedDish : dishes) {
            printLine(cookedDish.toString());
            printLine(cookedDish.getOrder().toString());
            printLine(cookedDish.getDish().toString());
            printLine(cookedDish.getCook().toString());
            printEmptyLine();
        }
    }

    /**
     * outputs list of orders (open or closed) and dishes in them
     * @param orderType     order's type - open or closed
     * @param orders        list of orders
     */
    public static void outputOrders(String orderType, List<Order> orders) {
        printLine("OPERATION SUCCEEDED! Current list of " + orderType + " orders: ");
        for (Order order : orders) {
            printLine(order.toString());
            ConsoleIO.outputDishes(order.getDishes());
        }
    }

    /**
     * outputs list of ingredients
     * @param ingredients       list of ingredients
     */
    public static void outputIngredients(List<Ingredient> ingredients) {
        printLine("Current list of ingredients: ");
        for (Ingredient ingredient: ingredients) {
            printLine(ingredient.toString());
        }

    }

    /**
     * provides input employee's data from console
     * @return      inputted employee
     */
    public static Employee inputEmployee() {
        Employee employee = new Employee();
        printLine("Please, input new employee's data: ");

        printLine("Input employee's surname: ");
        employee.setSurname(ConsoleInput.inputString());

        printLine("Input employee's name: ");
        employee.setName(ConsoleInput.inputString());

        printLine("Input employee's birthday: ");
        Date date = ConsoleInput.inputDate();
        employee.setBirthday(date);

        printLine("Input employee's position: ");
        employee.setPosition(ConsoleInput.inputString());

        printLine("Input employee's salary: ");
        employee.setSalary(ConsoleInput.inputFloat());

        return employee;
    }

    /**
     * provides input dish's data from console
     * @return      inputted dish
     */
    public static Dish inputDish() {
        Dish dish = new Dish();
        printLine("Please, input new dish's data: ");

        printLine("Input dish's name: ");
        dish.setName(ConsoleInput.inputString());

        printLine("Input dish's description: ");
        dish.setDescription(ConsoleInput.inputString());

        printLine("Input dish's cost: ");
        dish.setCost(ConsoleInput.inputFloat());

        printLine("Input dish's weight: ");
        dish.setWeight(ConsoleInput.inputInteger());

        return dish;
    }

    /**
     * provides input dish menu's data from console
     * @return      inputted dish menu
     */
    public static Menu inputMenu() {
        printLine("Please, input menu's name: ");
        String name = ConsoleInput.inputString();
        Menu menu = new Menu(name);
        return menu;
    }

    /**
     * provides input order's data from console
     * @return      inputted order
     */
    public static Order inputOrder() {
        printLine("Please, input date of order: ");
        Date date = ConsoleInput.inputDate();
        printLine("Please, input table number: ");
        int nTable = ConsoleInput.inputInteger();
        printLine("Please, input waiter's id: ");
        int waiterId = ConsoleInput.inputInteger();
        Order order = new Order(waiterId, nTable, date);
        return order;
    }

    /**
     * provides input ingredient's data from console
     * @return      inputted ingredient
     */
    public static Ingredient inputIngredient() {
        Ingredient ingredient = new Ingredient();
        printLine("Please, input new ingredient's data: ");

        printLine("Input ingredient's name: ");
        ingredient.setName(ConsoleInput.inputString());

        printLine("Input ingredient's start amount: ");
        ingredient.setAmount(ConsoleInput.inputInteger());

        return ingredient;
    }

}
