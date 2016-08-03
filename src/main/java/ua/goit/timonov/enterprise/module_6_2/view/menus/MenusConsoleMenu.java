package ua.goit.timonov.enterprise.module_6_2.view.menus;

import ua.goit.timonov.enterprise.module_6_2.controllers.DishController;
import ua.goit.timonov.enterprise.module_6_2.controllers.MenuController;
import ua.goit.timonov.enterprise.module_6_2.model.Dish;
import ua.goit.timonov.enterprise.module_6_2.model.Menu;
import ua.goit.timonov.enterprise.module_6_2.view.console.ConsoleIO;

import java.util.List;

/**
 * Created by Alex on 03.08.2016.
 */
public class MenusConsoleMenu extends ConsoleMenu {

    public static final String NAME = "name";
    public static final String MENU = "Menu";
    public static final String ID = "id";
    public static final String DISH = "dish";

    private MenuController menuController;
    private DishController dishController;

    public void setMenuController(MenuController menuController) {
        this.menuController = menuController;
    }

    public void setDishController(DishController dishController) {
        this.dishController = dishController;
    }

    public MenusConsoleMenu() {
        
        addItem(new ConsoleMenuItem("Get all Menus") {
            @Override
            public void run() {
                List<Menu> menus = menuController.getAll();
                ConsoleIO.outputMenus(menus);
            }
        });

        addItem(new ConsoleMenuItem("Add new Menu") {
            @Override
            public void run() {
                Menu newMenu = ConsoleIO.inputMenu();
                ConsoleIO.outputItem("Menu to add: ", newMenu.toString());
                menuController.add(newMenu);
            }
        });

        addItem(new ConsoleMenuItem("Search Menu by name") {
            @Override
            public void run() {
                String nameToSearch = ConsoleIO.inputString(MENU, NAME);
                Menu foundMenu = menuController.search(nameToSearch);
                ConsoleIO.outputItem("Found Menu: ", foundMenu.toString());
                ConsoleIO.outputDishes(foundMenu.getDishes());
            }
        });

        addItem(new ConsoleMenuItem("Search Menu by id") {
            @Override
            public void run() {
                Integer id = ConsoleIO.inputInteger(MENU, ID);
                Menu foundMenu = menuController.search(id);
                ConsoleIO.outputItem("Found Menu: ", foundMenu.toString());
                ConsoleIO.outputDishes(foundMenu.getDishes());
            }
        });

        addItem(new ConsoleMenuItem("Delete Menu by name") {
            @Override
            public void run() {
                String nameToDelete = ConsoleIO.inputString(MENU, NAME);
                menuController.delete(nameToDelete);
            }
        });

        addItem(new ConsoleMenuItem("Delete Menu by id") {
            @Override
            public void run() {
                Integer id = ConsoleIO.inputInteger(MENU, ID);
                menuController.delete(id);
            }
        });

        addItem(new ConsoleMenuItem("Add new dish to menu") {
            @Override
            public void run() {
                String menuName = ConsoleIO.inputString(MENU, NAME);
                Dish newDish = ConsoleIO.inputDish();
                dishController.add(newDish);
                newDish = dishController.search(newDish.getName());
                menuController.addDish(menuName, newDish);
                Menu menuWithAddedDish = menuController.search(menuName);
                ConsoleIO.outputItem("Found Menu: ", menuWithAddedDish.toString());
                ConsoleIO.outputDishes(menuWithAddedDish.getDishes());
            }
        });

        addItem(new ConsoleMenuItem("Add existing dish to menu") {
            @Override
            public void run() {
                String menuName = ConsoleIO.inputString(MENU, NAME);
                String dishName =  ConsoleIO.inputString(DISH, NAME);
                menuController.addDish(menuName, dishName);
                Menu menuWithAddedDish = menuController.search(menuName);
                ConsoleIO.outputItem("Found Menu: ", menuWithAddedDish.toString());
                ConsoleIO.outputDishes(menuWithAddedDish.getDishes());
            }
        });

        addItem(new ConsoleMenuItem("Delete dish from menu") {
            @Override
            public void run() {
                String menuName = ConsoleIO.inputString(MENU, NAME);
                String dishName = ConsoleIO.inputString(DISH, NAME);
                menuController.deleteDish(menuName, dishName);
                Menu menuWithDeletedDish = menuController.search(menuName);
                ConsoleIO.outputItem("Found Menu: ", menuWithDeletedDish.toString());
                ConsoleIO.outputDishes(menuWithDeletedDish.getDishes());
            }
        });

    }
}
