package ua.goit.timonov.enterprise.module_6_2.view.menus;

import ua.goit.timonov.enterprise.module_6_2.controllers.DishController;
import ua.goit.timonov.enterprise.module_6_2.controllers.MenuController;
import ua.goit.timonov.enterprise.module_6_2.model.Dish;
import ua.goit.timonov.enterprise.module_6_2.model.Menu;
import ua.goit.timonov.enterprise.module_6_2.view.console.ConsoleIO;

import java.util.List;

import static ua.goit.timonov.enterprise.module_6_2.view.console.ConsolePrinter.printLine;

/**
 * Created by Alex on 03.08.2016.
 */
public class MenusConsoleMenu extends ConsoleMenu {

    public static final String NAME = "name";
    public static final String MENU = "Menu";
    public static final String ID = "id";
    public static final String DISH = "dish";
    public static final String NO_SUCCESS = "UNSUCCESSFUL! There's no menu with such ";

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
                try {
                    List<Menu> menus = menuController.getAll();
                    ConsoleIO.outputMenus(menus);
                } catch (RuntimeException e) {
                    printLine("UNSUCCESSFUL! There's no menus in the base!");
                }
            }
        });

        addItem(new ConsoleMenuItem("Add new Menu") {
            @Override
            public void run() {
                Menu newMenu = ConsoleIO.inputMenu();
                ConsoleIO.outputItem("Menu to add: ", newMenu.toString());
                try {
                    menuController.add(newMenu);
                    printLine(SUCCESS);
                } catch (RuntimeException e) {
                    ConsoleIO.outputItem(e.getMessage(), newMenu.getName());
                }
            }
        });

        addItem(new ConsoleMenuItem("Search Menu by id") {
            @Override
            public void run() {
                Integer id = ConsoleIO.inputInteger(MENU, ID);
                try {
                    Menu foundMenu = menuController.search(id);
                    ConsoleIO.outputItem(SUCCESS + "Found Menu: ", foundMenu.toString());
                    ConsoleIO.outputDishes(foundMenu.getDishes());
                } catch (RuntimeException e) {
                    ConsoleIO.outputItem(NO_SUCCESS + ID, String.valueOf(id));
                }
            }
        });

        addItem(new ConsoleMenuItem("Search Menu by name") {
            @Override
            public void run() {
                String nameToSearch = ConsoleIO.inputString(MENU, NAME);
                try {
                    Menu foundMenu = menuController.search(nameToSearch);
                    ConsoleIO.outputItem(SUCCESS + "Found Menu: ", foundMenu.toString());
                    ConsoleIO.outputDishes(foundMenu.getDishes());
                } catch (RuntimeException e) {
                    ConsoleIO.outputItem(NO_SUCCESS + NAME, nameToSearch);
                }
            }
        });

        addItem(new ConsoleMenuItem("Delete Menu by id") {
            @Override
            public void run() {
                Integer id = ConsoleIO.inputInteger(MENU, ID);
                try {
                    menuController.delete(id);
                    printLine(SUCCESS);
                } catch (RuntimeException e) {
                    ConsoleIO.outputItem(NO_SUCCESS + ID, String.valueOf(id));
                }
            }
        });

        addItem(new ConsoleMenuItem("Delete Menu by name") {
            @Override
            public void run() {
                String nameDish = ConsoleIO.inputString(MENU, NAME);
                try {
                    menuController.delete(nameDish);
                    printLine(SUCCESS);
                } catch (RuntimeException e) {
                    ConsoleIO.outputItem(NO_SUCCESS + NAME, nameDish);
                }
            }
        });

        addItem(new ConsoleMenuItem("Add  dish to menu") {
            @Override
            public void run() {
                String menuName = ConsoleIO.inputString(MENU, NAME);
                String dishName =  ConsoleIO.inputString(DISH, NAME);
                try {
                    Dish dish = dishController.search(dishName);
                    Menu menu = menuController.search(menuName);
                    menuController.addDish(menu, dish);
                    Menu menuWithAddedDish = menuController.search(menu.getId());
                    ConsoleIO.outputItem(SUCCESS + "Changed Menu: ", menuWithAddedDish.toString());
                    ConsoleIO.outputDishes(menuWithAddedDish.getDishes());
                } catch (RuntimeException e) {
                    printLine("UNSUCCESSFUL! Dish was not added to menu");
                }
            }
        });

        addItem(new ConsoleMenuItem("Delete dish from menu") {
            @Override
            public void run() {
                String menuName = ConsoleIO.inputString(MENU, NAME);
                String dishName = ConsoleIO.inputString(DISH, NAME);
                try {
                    Dish dish = dishController.search(dishName);
                    Menu menu = menuController.search(menuName);
                    menuController.deleteDish(menu, dish);
                    Menu menuWithDeletedDish = menuController.search(menuName);
                    ConsoleIO.outputItem(SUCCESS + "Changed menu: ", menuWithDeletedDish.toString());
                    ConsoleIO.outputDishes(menuWithDeletedDish.getDishes());
                } catch (Exception e) {
                    printLine("UNSUCCESSFUL! Dish " + dishName + " was not deleted from " + menuName + " menu");
                }
            }
        });
    }
}
