package ua.goit.timonov.enterprise.module_6_2.view.menus;

import ua.goit.timonov.enterprise.module_6_2.controllers.DishController;
import ua.goit.timonov.enterprise.module_6_2.model.DbItem;
import ua.goit.timonov.enterprise.module_6_2.model.Dish;
import ua.goit.timonov.enterprise.module_6_2.view.console.ConsoleIO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handler for tasks with DB Restaurant's component Dish
 * with implementation of methods from DbItemHandler:
 * - get from DB list of all dishes
 * - add new dish to DB
 * - search dish by ID
 * - search dish by name
 * - delete dish from DB by ID
 * - delete dish from DB by name
 */
public class DishesHandler extends DbItemHandler {

    public static final String DISH = "dish";

    /* controller for tasks with dishes */
    private DishController dishController;

    public void setDishController(DishController dishController) {
        this.dishController = dishController;
    }

    /**
     * Uses inherited constructor with setting component's name
     */
    public DishesHandler() {
        super(DISH);
    }

    // implementation of inherited methods from DbItemHandler
    @Override
    protected List<DbItem> getAllItems() {
        List<Dish> dishes = dishController.getAll();
        List<DbItem> items = dishes.stream().collect(Collectors.toList());
        return items;
    }

    @Override
    protected void outputItemList(List<DbItem> itemList) {
        List<Dish> dishes = new ArrayList<>();
        for (DbItem dbItem : itemList) {
            dishes.add((Dish) dbItem);
        }
        ConsoleIO.outputDishes(dishes);
    }

    @Override
    protected DbItem inputItem() {
        return ConsoleIO.inputDish();
    }

    @Override
    protected void addItem(DbItem newItem) {
        dishController.add((Dish) newItem);
    }

    @Override
    protected DbItem searchItem(int id) {
        return dishController.search(id);
    }

    @Override
    protected DbItem searchItem(String... name) {
        return dishController.search(name[0]);
    }

    @Override
    protected void deleteItem(int id) {
        dishController.delete(id);
    }

    @Override
    protected void deleteItem(String... name) {
        dishController.delete(name[0]);
    }
}
