package ua.goit.timonov.enterprise.module_6_2.view.menus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.goit.timonov.enterprise.module_6_2.controllers.DishController;
import ua.goit.timonov.enterprise.module_6_2.controllers.MenuController;
import ua.goit.timonov.enterprise.module_6_2.exceptions.UserRefuseInputException;
import ua.goit.timonov.enterprise.module_6_2.model.Dish;
import ua.goit.timonov.enterprise.module_6_2.model.Menu;
import ua.goit.timonov.enterprise.module_6_2.view.console.ConsoleIO;

import java.util.List;

/**
 * Handler for tasks with DB Restaurant's component Menu (dish menu)
 * with implementation of methods from DbItemHandler:
 * - get from DB list of all menus
 * - add new menu to DB
 * - search menu by ID
 * - search menu by name
 * - delete menu from DB by ID
 * - search menu from DB by name
 * and additional tasks:
 * - add dish to menu
 * - delete dish from menu
 */
public class MenusHandler extends DbItemHandler<Menu> {

    public static final String MENU = "Menu";
    public static final String DISH = "dish";

    public static Logger LOGGER = LoggerFactory.getLogger(MenusHandler.class);

    private MenuController menuController;

    private DishController dishController;

    public void setMenuController(MenuController menuController) {
        this.menuController = menuController;
    }

    public void setDishController(DishController dishController) {
        this.dishController = dishController;
    }

    /**
     * Uses inherited constructor with setting component's name
     * and adds to handler additional methods
     */
    public MenusHandler() {
        super(MENU);

        String nameMenuItem = "Add  dish to menu";
        consoleMenu.addItem(new ConsoleMenuItem(nameMenuItem, () -> addDishToMenu()));

        nameMenuItem = "Delete dish from menu";
        consoleMenu.addItem(new ConsoleMenuItem(nameMenuItem, () -> deleteDishFromMenu()));
    }

    // adds dish to menu
    // provides user input of dish's and menu's name, outputs message about success or error
    private void addDishToMenu() {
        try {
            String menuName = ConsoleIO.inputString(MENU, NAME);
            Menu menu = menuController.search(menuName);
            String dishName =  ConsoleIO.inputString(DISH, NAME);
            Dish dish = dishController.search(dishName);
            menuController.addDish(menu, dish);
            Menu menuWithAddedDish = menuController.search(menu.getId());
            ConsoleIO.outputItem(SUCCESS + " Changed Menu: ", menuWithAddedDish.toString());
            ConsoleIO.outputDishes(menuWithAddedDish.getDishes());
        }
        catch (UserRefuseInputException e ) {
            LOGGER.info(e.getMessage());
        }
        catch (RuntimeException e) {
            LOGGER.error("UNSUCCESSFUL! Dish was not added to menu");
        }
    }

    // deletes dish from menu
    // provides user input of dish's and menu's name, outputs message about success or error
    private void deleteDishFromMenu() {
        try {
            String menuName = ConsoleIO.inputString(MENU, NAME);
            String dishName = ConsoleIO.inputString(DISH, NAME);
            Dish dish = dishController.search(dishName);
            Menu menu = menuController.search(menuName);
            menuController.deleteDish(menu, dish);
            Menu menuWithDeletedDish = menuController.search(menuName);
            ConsoleIO.outputItem(SUCCESS + " Changed menu: ", menuWithDeletedDish.toString());
            ConsoleIO.outputDishes(menuWithDeletedDish.getDishes());
        }
        catch (UserRefuseInputException e ) {
            LOGGER.info(e.getMessage());
        }
        catch (RuntimeException e) {
            LOGGER.error("UNSUCCESSFUL! Dish was not deleted from menu");
        }
    }

    // implementation of inherited methods from DbItemHandler
    @Override
    protected List<Menu> getAllItems() {
        return menuController.getAll();
    }

    @Override
    protected void outputItemList(List<Menu> menus) {
        ConsoleIO.outputMenus(menus);
    }

    @Override
    protected String getName(Menu item) {
        return null;
    }

    @Override
    protected Menu inputItem() {
        return ConsoleIO.inputMenu();
    }

    @Override
    protected void addItem(Menu menu) {
        menuController.add(menu);
    }

    @Override
    protected Menu searchItem(int id) {
        return menuController.search(id);
    }

    @Override
    protected Menu searchItem(String... name) {
        return menuController.search(name[0]);
    }

    @Override
    protected void deleteItem(int id) {
        menuController.delete(id);
    }

    @Override
    protected void deleteItem(String... name) {
        menuController.delete(name[0]);
    }
}



