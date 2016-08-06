package ua.goit.timonov.enterprise.module_6_2.view.menus;

import ua.goit.timonov.enterprise.module_6_2.controllers.CookedDishController;
import ua.goit.timonov.enterprise.module_6_2.model.CookedDish;
import ua.goit.timonov.enterprise.module_6_2.view.console.ConsoleIO;

import java.util.List;

import static ua.goit.timonov.enterprise.module_6_2.view.console.ConsolePrinter.printLine;

/**
 * Console menu for tasks with cooked dishes
 */
public class CookedDishConsoleMenu extends ConsoleMenu {

    public static final String ID = "id";
    public static final String COOKED_DISH = "cooked dish's";
    public static final String COOK = "cook's";
    public static final String ORDER = "order's";
    public static final String DISH_NAME = "dish name";
    public static final String NAME = "name";

    /* controller for tasks with cooked dishes */
    private CookedDishController cookedDishController;

    public void setCookedDishController(CookedDishController cookedDishController) {
        this.cookedDishController = cookedDishController;
    }

    /**
     * configures menu's items:
     * - get from DB list of cooked dishes
     * - ddd cooked dish by order's ID, dish's name, cook's ID
     */
    public CookedDishConsoleMenu() {

        addItem(new ConsoleMenuItem("Get all cooked dishes") {
            @Override
            public void run() {
                try {
                    List<CookedDish> cookedDishes = cookedDishController.getAll();
                    ConsoleIO.outputCookedDish(cookedDishes);
                } catch (RuntimeException e) {
                    printLine("UNSUCCESSFUL! There's no cooked dishes in the base!");
                }
            }
        });

        addItem(new ConsoleMenuItem("Add by CLOSED order's id, dish's name, cook's id") {
            @Override
            public void run() {
                Integer orderId = ConsoleIO.inputInteger(ORDER, ID);
                String dishName = ConsoleIO.inputString(DISH_NAME, NAME);
                Integer cookId = ConsoleIO.inputInteger(COOK, ID);
                try {
                    cookedDishController.add(orderId, dishName, cookId);
                    List<CookedDish> cookedDishes = cookedDishController.getAll();
                    ConsoleIO.outputCookedDish(cookedDishes);
                } catch (RuntimeException e) {
                    printLine("UNSUCCESSFUL! Unable to add dish to cooked dishes");
                }
            }
        });
    }
}
