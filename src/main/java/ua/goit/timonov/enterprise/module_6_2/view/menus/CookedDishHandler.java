package ua.goit.timonov.enterprise.module_6_2.view.menus;

import ua.goit.timonov.enterprise.module_6_2.controllers.CookedDishController;
import ua.goit.timonov.enterprise.module_6_2.controllers.DishController;
import ua.goit.timonov.enterprise.module_6_2.controllers.EmployeeController;
import ua.goit.timonov.enterprise.module_6_2.controllers.OrderController;
import ua.goit.timonov.enterprise.module_6_2.model.*;
import ua.goit.timonov.enterprise.module_6_2.view.console.ConsoleIO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handler for tasks with DB Restaurant's component CookedDish
 * with implementation of methods from DbItemHandlerWithBaseMethods:
 * - get from DB list of all cooked dishes
 * - add new cooked dish
 */
public class CookedDishHandler extends DbItemHandlerWithBaseMethods {

    public static final String COOK = "cook's";
    public static final String ORDER = "order's";
    public static final String DISH_NAME = "dish name";
    public static final String NAME = "name";
    public static final String COOKED_DISH = "Cooked dish";

    private CookedDishController cookedDishController;
    private OrderController orderController;
    private DishController dishController;
    private EmployeeController employeeController;

    public void setCookedDishController(CookedDishController cookedDishController) {
        this.cookedDishController = cookedDishController;
    }

    public void setOrderController(OrderController orderController) {
        this.orderController = orderController;
    }

    public void setDishController(DishController dishController) {
        this.dishController = dishController;
    }

    public void setEmployeeController(EmployeeController employeeController) {
        this.employeeController = employeeController;
    }

    /**
     * Uses inherited constructor with setting component's name
     */
    public CookedDishHandler() {
        super(COOKED_DISH);
    }

    // implementation of inherited methods from DbItemHandlerWithBaseMethods
    @Override
    protected List<DbItem> getAllItems() {
        List<CookedDish> cookedDishes = cookedDishController.getAll();
        List<DbItem> items = cookedDishes.stream().collect(Collectors.toList());
        return items;
    }

    @Override
    protected void outputItemList(List<DbItem> itemList) {
        List<CookedDish> cookedDishes = new ArrayList<>();
        for (DbItem dbItem : itemList) {
            cookedDishes.add((CookedDish) dbItem);
        }
        ConsoleIO.outputCookedDish(cookedDishes);
    }

    @Override
    protected DbItem inputItem() {
        int orderId = ConsoleIO.inputInteger(ORDER, ID);
        String dishName = ConsoleIO.inputString(DISH_NAME, NAME);
        int cookId = ConsoleIO.inputInteger(COOK, ID);

        Order order = orderController.search(orderId);
        Dish dish = dishController.search(dishName);
        Employee cook = employeeController.search(cookId);
        CookedDish cookedDish = new CookedDish(order, dish, cook);
        return cookedDish;
    }


    @Override
    protected void addItem(DbItem newItem) {
        cookedDishController.add((CookedDish) newItem);
    }
}
