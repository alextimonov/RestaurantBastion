package ua.goit.timonov.enterprise.module_6_2.view.menus;

import ua.goit.timonov.enterprise.module_6_2.controllers.DishController;
import ua.goit.timonov.enterprise.module_6_2.model.Dish;
import ua.goit.timonov.enterprise.module_6_2.view.console.ConsoleIO;

import java.util.List;

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
public class DishesHandler extends DbItemHandler<Dish> {

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
    protected List<Dish> getAllItems() {
        return dishController.getAll();
    }

    @Override
    protected void outputItemList(List<Dish> dishes) {
        ConsoleIO.outputDishes(dishes);
    }

    @Override
    protected String getName(Dish dish) {
        return dish.getName();
    }

    @Override
    protected Dish inputItem() {
        return ConsoleIO.inputDish();
    }

    @Override
    protected void addItem(Dish dish) {
        dishController.add(dish);
    }

    @Override
    protected Dish searchItem(int id) {
        return dishController.search(id);
    }

    @Override
    protected Dish searchItem(String... name) {
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
