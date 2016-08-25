package ua.goit.timonov.enterprise.module_6_2.view.console;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.goit.timonov.enterprise.module_6_2.model.*;

import java.util.Date;
import java.util.List;

/**
 * Provides input & output operations with console
 */
public class ConsoleIO {

    public static final String PLEASE_INPUT = "Please input ";
    public static final String POSSESSIVE = "'s ";
    public static final String COLON = ": ";

    private static Logger LOGGER = LoggerFactory.getLogger(ConsoleIO.class);

    /**
     * provides input of string from console
     * @param itemName      item's (table's) name
     * @param fieldName     field's name
     * @return              inputted string
     */
    public static String inputString(String itemName, String fieldName) {
        LOGGER.info(PLEASE_INPUT + itemName + POSSESSIVE + fieldName + COLON);
        return ConsoleInput.inputString();
    }

    /**
     * provides input of Integer from console
     * @param itemName      item's (table's) name
     * @param fieldName     field's name
     * @return              inputted integer value
     */
    public static Integer inputInteger(String itemName, String fieldName) {
        LOGGER.info(PLEASE_INPUT + itemName + POSSESSIVE + fieldName + COLON);
        return ConsoleInput.inputInteger();
    }


    /**
     * outputs string item and explanation to it
     * @param explain       explanation to outputed item
     * @param item          item to output
     */
    public static void outputItem(String explain, String item) {
        LOGGER.info(explain);
        LOGGER.info(item);
    }

    /**
     * outputs List of employees
     * @param employees         List of string employees to output
     */
    public static void outputEmployees(List<Employee> employees) {
        LOGGER.info("OPERATION SUCCEEDED! Current list of employees: ");
        for (Employee employee : employees) {
            LOGGER.info(employee.toString());
        }
    }

    /**
     * outputs List of dishes
     * @param dishes         List of string employees to output
     */
    public static void outputDishes(List<Dish> dishes) {
        LOGGER.info("Current list of dishes: ");
        for (Dish dish : dishes) {
            LOGGER.info(dish.toString());
        }
        LOGGER.info("");
    }

    /**
     * outputs List of menus and dishes in each menu
     * @param menus         List of menus to ouput
     */
    public static void outputMenus(List<Menu> menus) {
        LOGGER.info("OPERATION SUCCEEDED! Current list of menus: ");
        for (Menu menu : menus) {
            LOGGER.info(menu.toString());
            outputDishes(menu.getDishes());
        }
    }

    /**
     * outputs List of cooked dishes and its orders, cooks and dishes associated with each cooked dish
     * @param dishes
     */
    public static void outputCookedDish(List<CookedDish> dishes) {
        LOGGER.info("OPERATION SUCCEEDED! Current list of cooked dishes: ");
        for (CookedDish cookedDish : dishes) {
            LOGGER.info(cookedDish.toString());
            LOGGER.info(cookedDish.getOrder().toString());
            LOGGER.info(cookedDish.getDish().toString());
            LOGGER.info(cookedDish.getCook().toString());
            LOGGER.info("");
        }
    }

    /**
     * outputs list of orders (open or closed) and dishes in them
     * @param orderType     order's type - open or closed
     * @param orders        list of orders
     */
    public static void outputOrders(String orderType, List<Order> orders) {
        LOGGER.info("OPERATION SUCCEEDED! Current list of " + orderType + " orders: ");
        for (Order order : orders) {
            LOGGER.info(order.toString());
            ConsoleIO.outputDishes(order.getDishes());
        }
    }

    /**
     * outputs list of ingredients
     * @param ingredients       list of ingredients
     */
    public static void outputIngredients(List<Ingredient> ingredients) {
        LOGGER.info("Current list of ingredients: ");
        for (Ingredient ingredient: ingredients) {
            LOGGER.info(ingredient.toString());
        }

    }

    /**
     * provides input employee's data from console
     * @return      inputted employee
     */
    public static Employee inputEmployee() {
        Employee employee = new Employee();
        LOGGER.info("Please, input new employee's data: ");

        LOGGER.info("Input employee's surname: ");
        employee.setSurname(ConsoleInput.inputString());

        LOGGER.info("Input employee's name: ");
        employee.setName(ConsoleInput.inputString());

        LOGGER.info("Input employee's birthday: ");
        Date date = ConsoleInput.inputDate();
        employee.setBirthday(date);

        LOGGER.info("Input employee's position: ");
        employee.setPosition(ConsoleInput.inputString());

        LOGGER.info("Input employee's salary: ");
        employee.setSalary(ConsoleInput.inputFloat());

        return employee;
    }

    /**
     * provides input dish's data from console
     * @return      inputted dish
     */
    public static Dish inputDish() {
        Dish dish = new Dish();
        LOGGER.info("Please, input new dish's data: ");

        LOGGER.info("Input dish's name: ");
        dish.setName(ConsoleInput.inputString());

        LOGGER.info("Input dish's description: ");
        dish.setDescription(ConsoleInput.inputString());

        LOGGER.info("Input dish's cost: ");
        dish.setCost(ConsoleInput.inputFloat());

        LOGGER.info("Input dish's weight: ");
        dish.setWeight(ConsoleInput.inputInteger());

        return dish;
    }

    /**
     * provides input dish menu's data from console
     * @return      inputted dish menu
     */
    public static Menu inputMenu() {
        LOGGER.info("Please, input menu's name: ");
        String name = ConsoleInput.inputString();
        Menu menu = new Menu(name);
        return menu;
    }

    /**
     * provides input order's data from console
     * @return      inputted order
     */
    public static Order inputOrder() {
        LOGGER.info("Please, input date of order: ");
        Date date = ConsoleInput.inputDate();
        LOGGER.info("Please, input table number: ");
        int nTable = ConsoleInput.inputInteger();
        LOGGER.info("Please, input waiter's id: ");
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
        LOGGER.info("Please, input new ingredient's data: ");

        LOGGER.info("Input ingredient's name: ");
        ingredient.setName(ConsoleInput.inputString());

        LOGGER.info("Input ingredient's start amount: ");
        ingredient.setAmount(ConsoleInput.inputInteger());

        return ingredient;
    }

}
