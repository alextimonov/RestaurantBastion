package ua.goit.timonov.enterprise.module_6_2.view.menus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.goit.timonov.enterprise.module_6_2.controllers.DishController;
import ua.goit.timonov.enterprise.module_6_2.controllers.OrderController;
import ua.goit.timonov.enterprise.module_6_2.exceptions.UserRefuseInputException;
import ua.goit.timonov.enterprise.module_6_2.model.DbItem;
import ua.goit.timonov.enterprise.module_6_2.model.Dish;
import ua.goit.timonov.enterprise.module_6_2.model.Order;
import ua.goit.timonov.enterprise.module_6_2.view.console.ConsoleIO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handler for tasks with DB Restaurant's component Orders
 * with implementation of methods from DbItemHandlerWithBaseMethods:
 * - get from DB list of open orders
 * - add new order
 * and additional tasks:
 * - get from DB list of all closed orders
 * - delete open order from DB by ID
 * - set open order to closed
 * - add dish to open order
 * - delete dish from open order
 */
public class OrdersHandler extends DbItemHandlerWithBaseMethods {

    public static final String ORDER = "Order";
    public static final String OPEN = "open";
    public static final String CLOSED = "closed";
    public static final String DISH = "dish";
    public static final String NAME = "name";
    public static final String NO_SUCCESS = "UNSUCCESSFUL! There's no order with such ";

    public static Logger LOGGER = LoggerFactory.getLogger(OrdersHandler.class);

    private OrderController orderController;
    private DishController dishController;

    public void setOrderController(OrderController orderController) {
        this.orderController = orderController;
    }

    public void setDishController(DishController dishController) {
        this.dishController = dishController;
    }

    /**
     * Uses inherited constructor with setting component's name
     * and adds to handler additional methods
     */
    public OrdersHandler() {
        super(ORDER);
        consoleMenu.items.get(0).setTitle("Get all open orders");

        String nameMenuItem = "Get all closed orders";
        consoleMenu.items.add(1, new ConsoleMenuItem(nameMenuItem, () -> getClosedOrders()));

        nameMenuItem = "Delete open order";
        consoleMenu.addItem(new ConsoleMenuItem(nameMenuItem, () -> deleteOpenOrder()));

        nameMenuItem = "Set open order to closed";
        consoleMenu.addItem(new ConsoleMenuItem(nameMenuItem, () -> setOrderToClosed()));

        nameMenuItem = "Add dish to open order";
        consoleMenu.addItem(new ConsoleMenuItem(nameMenuItem, () -> addDishToOrder()));

        nameMenuItem = "Delete dish from open order";
        consoleMenu.addItem(new ConsoleMenuItem(nameMenuItem, () -> deleteDishFromOrder()));
    }

    // get from DB list of all closed orders
    private void getClosedOrders() {
        try {
            List<Order> closedOrders = orderController.getClosedOrders();
            ConsoleIO.outputOrders(CLOSED, closedOrders);
        }
        catch (RuntimeException e) {
            LOGGER.error("UNSUCCESSFUL! There's no closed orders in the base!");
        }
    }

    // delete open order from DB by ID
    private void deleteOpenOrder() {
        int orderId = 0;
        try {
            orderId = ConsoleIO.inputInteger(ORDER, ID);
            orderController.delete(orderId);
            LOGGER.info(SUCCESS);
        }
        catch (UserRefuseInputException e) {
            LOGGER.info(e.getMessage());
        }
        catch (RuntimeException e) {
            ConsoleIO.outputItem(NO_SUCCESS + ID, String.valueOf(orderId));
        }
    }

    // set open order to closed
    private void setOrderToClosed() {
        int orderId = 0;
        try {
            orderId = ConsoleIO.inputInteger(ORDER, ID);
            orderController.setClosed(orderId);
            LOGGER.info(SUCCESS);
        }
        catch (UserRefuseInputException e) {
            LOGGER.info(e.getMessage());
        }
        catch (RuntimeException e) {
            ConsoleIO.outputItem("UNSUCCESSFUL! Unable to set to closed order with " + ID, String.valueOf(orderId));
        }
    }

    // add dish to open order
    private void addDishToOrder() {
        try {
            int orderId = ConsoleIO.inputInteger(ORDER, ID);
            String dishName = ConsoleIO.inputString(DISH, NAME);
            Dish dish = dishController.search(dishName);
            orderController.addDish(orderId, dish);
            Order order = orderController.search(orderId);
            ConsoleIO.outputItem(SUCCESS + " Changed order", order.toString());
            ConsoleIO.outputDishes(order.getDishes());
        }
        catch (UserRefuseInputException e) {
            LOGGER.info(e.getMessage());
        }
        catch (RuntimeException e) {
            LOGGER.error("UNSUCCESSFUL! Dish was not added to order");
        }
    }

    //  delete dish from open order
    private void deleteDishFromOrder() {
        try {
            int orderId = ConsoleIO.inputInteger(ORDER, ID);
            String dishName = ConsoleIO.inputString(DISH, NAME);
            Dish dish = dishController.search(dishName);
            orderController.deleteDish(orderId, dish);
            Order order = orderController.search(orderId);
            ConsoleIO.outputItem(SUCCESS + " Changed order:", order.toString());
            ConsoleIO.outputDishes(order.getDishes());
        }
        catch (UserRefuseInputException e) {
            LOGGER.info(e.getMessage());
        }
        catch (RuntimeException e) {
            LOGGER.error("UNSUCCESSFUL! Dish was not deleted from order");
        }
    }

    // implementation of inherited methods from DbItemHandlerWithBaseMethods
    @Override
    protected List<DbItem> getAllItems() {
        List<Order> orders = orderController.getOpenOrders();
        List<DbItem> items = orders.stream().collect(Collectors.toList());
        return items;
    }

    @Override
    protected void outputItemList(List<DbItem> itemList) {
        List<Order> orders = new ArrayList<>();
        for (DbItem dbItem : itemList) {
            orders.add((Order) dbItem);
        }
        ConsoleIO.outputOrders(OPEN, orders);
    }

    @Override
    protected DbItem inputItem() {
        return ConsoleIO.inputOrder();
    }

    @Override
    protected void addItem(DbItem newItem) {
        orderController.add((Order) newItem);
    }
}