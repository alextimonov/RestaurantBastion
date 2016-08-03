package ua.goit.timonov.enterprise.module_6_2.view.menus;

import ua.goit.timonov.enterprise.module_6_2.controllers.DishController;
import ua.goit.timonov.enterprise.module_6_2.model.Dish;
import ua.goit.timonov.enterprise.module_6_2.view.console.ConsoleIO;

import java.util.List;

/**
 * Created by Alex on 03.08.2016.
 */
public class DishesConsoleMenu extends ConsoleMenu {

    public static final String DISHES = "dishes";
    public static final String ID = "id";
    public static final String DISH = "dish";
    public static final String NAME = "name";
    private DishController dishController;

    public void setDishController(DishController dishController) {
        this.dishController = dishController;
    }

    public DishesConsoleMenu() {

        addItem(new ConsoleMenuItem("Get all dishes") {
            @Override
            public void run() {
                List<Dish> dishes = dishController.getAll();
                ConsoleIO.outputDishes(dishes);
            }
        });

        addItem(new ConsoleMenuItem("Add new dish") {
            @Override
            public void run() {
                Dish newDish = ConsoleIO.inputDish();
                ConsoleIO.outputItem("Dish to add: ", newDish.toString());
                dishController.add(newDish);
            }
        });

        addItem(new ConsoleMenuItem("Search dish by name") {
            @Override
            public void run() {
                String nameToSearch = ConsoleIO.inputString(DISH, NAME);
                Dish foundDish = dishController.search(nameToSearch);
                ConsoleIO.outputItem("Found dish: ", foundDish.toString());
            }
        });

        addItem(new ConsoleMenuItem("Search dish by id") {
            @Override
            public void run() {
                Integer id = ConsoleIO.inputInteger(DISH, ID);
                Dish foundDish = dishController.search(id);
                ConsoleIO.outputItem("Found dish: ", foundDish.toString());
            }
        });

        addItem(new ConsoleMenuItem("Delete dish by id") {
            @Override
            public void run() {
                Integer id = ConsoleIO.inputInteger(DISH, ID);
                dishController.delete(id);
            }
        });

        addItem(new ConsoleMenuItem("Delete dish by name") {
            @Override
            public void run() {
                String nameDish = ConsoleIO.inputString(DISH, NAME);
                dishController.delete(nameDish);
            }
        });


    }
}
