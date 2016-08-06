package ua.goit.timonov.enterprise.module_6_2.view.menus;

import ua.goit.timonov.enterprise.module_6_2.controllers.DishController;
import ua.goit.timonov.enterprise.module_6_2.model.Dish;
import ua.goit.timonov.enterprise.module_6_2.view.console.ConsoleIO;

import java.util.List;

import static ua.goit.timonov.enterprise.module_6_2.view.console.ConsolePrinter.printLine;

/**
 * Created by Alex on 03.08.2016.
 */
public class DishesConsoleMenu extends ConsoleMenu {

    public static final String DISHES = "dishes";
    public static final String DISH = "dish";
    public static final String NAME = "name";
    public static final String NO_SUCCESS = "UNSUCCESSFUL! There's no dish with such ";

    private DishController dishController;

    public void setDishController(DishController dishController) {
        this.dishController = dishController;
    }

    public DishesConsoleMenu() {

        addItem(new ConsoleMenuItem("Get all dishes") {
            @Override
            public void run() {
                try {
                    List<Dish> dishes = dishController.getAll();
                    ConsoleIO.outputDishes(dishes);
                } catch (RuntimeException e) {
                    printLine("UNSUCCESSFUL! There's no dishes in the base!");
                }
            }
        });

        addItem(new ConsoleMenuItem("Add new dish") {
            @Override
            public void run() {
                Dish newDish = ConsoleIO.inputDish();
                ConsoleIO.outputItem("Dish to add: ", newDish.toString());
                try {
                    dishController.add(newDish);
                    printLine(SUCCESS);
                } catch (RuntimeException e) {
                    ConsoleIO.outputItem(e.getMessage(), newDish.getName());
                }
            }
        });

        addItem(new ConsoleMenuItem("Search dish by id") {
            @Override
            public void run() {
                Integer id = ConsoleIO.inputInteger(DISH, ID);
                try {
                    Dish foundDish = dishController.search(id);
                    ConsoleIO.outputItem(SUCCESS + " Found dish: ", foundDish.toString());
                } catch (RuntimeException e) {
                    ConsoleIO.outputItem(NO_SUCCESS + ID, String.valueOf(id));
                }
            }
        });

        addItem(new ConsoleMenuItem("Search dish by name") {
            @Override
            public void run() {
                String nameToSearch = ConsoleIO.inputString(DISH, NAME);
                try {
                    Dish foundDish = dishController.search(nameToSearch);
                    ConsoleIO.outputItem(SUCCESS + "Found dish: ", foundDish.toString());
                } catch (RuntimeException e) {
                    ConsoleIO.outputItem(NO_SUCCESS + NAME, nameToSearch);
                }
            }
        });

        addItem(new ConsoleMenuItem("Delete dish by id") {
            @Override
            public void run() {
                Integer id = ConsoleIO.inputInteger(DISH, ID);
                try {
                    dishController.delete(id);
                    printLine(SUCCESS);
                } catch (RuntimeException e) {
                    ConsoleIO.outputItem(NO_SUCCESS + ID, String.valueOf(id));
                }
            }
        });

        addItem(new ConsoleMenuItem("Delete dish by name") {
            @Override
            public void run() {
                String nameDish = ConsoleIO.inputString(DISH, NAME);
                try {
                    dishController.delete(nameDish);
                    printLine(SUCCESS);
                } catch (RuntimeException e) {
                    ConsoleIO.outputItem(NO_SUCCESS + NAME, nameDish);
                }
            }
        });
    }
}
