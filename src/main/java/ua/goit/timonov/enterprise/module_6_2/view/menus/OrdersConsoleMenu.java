package ua.goit.timonov.enterprise.module_6_2.view.menus;

import ua.goit.timonov.enterprise.module_6_2.controllers.DishController;
import ua.goit.timonov.enterprise.module_6_2.controllers.OrderController;
import ua.goit.timonov.enterprise.module_6_2.model.Order;
import ua.goit.timonov.enterprise.module_6_2.view.console.ConsoleIO;

import java.util.List;

/**
 * Created by Alex on 03.08.2016.
 */
public class OrdersConsoleMenu extends ConsoleMenu {

    public static final String OPEN = "open";
    public static final String ORDER = "order";
    public static final String ID = "id";
    public static final String DISH = "dish";
    public static final String NAME = "name";
    public static final String CLOSED = "closed";
    private OrderController orderController;
    private DishController dishController;

    public void setOrderController(OrderController orderController) {
        this.orderController = orderController;
    }

    public void setDishController(DishController dishController) {
        this.dishController = dishController;
    }

    public OrdersConsoleMenu() {

        addItem(new ConsoleMenuItem("Get all open orders") {
            @Override
            public void run() {
                List<Order> openOrders = orderController.getOpenOrders();
                ConsoleIO.outputOrders(OPEN, openOrders);
            }
        });

        addItem(new ConsoleMenuItem("Get all closed orders") {
            @Override
            public void run() {
                List<Order> closedOrders = orderController.getClosedOrders();
                ConsoleIO.outputOrders(CLOSED, closedOrders);
            }
        });

        addItem(new ConsoleMenuItem("Add new open order") {
            @Override
            public void run() {
                Order newOrder = ConsoleIO.inputOrder();
                ConsoleIO.outputItem("Order to add: ", newOrder.toString());
                orderController.add(newOrder);
            }
        });

        addItem(new ConsoleMenuItem("Delete open order") {
            @Override
            public void run() {
                int orderId = ConsoleIO.inputInteger(ORDER, ID);
                orderController.delete(orderId);
            }
        });

        addItem(new ConsoleMenuItem("Set open order to closed") {
            @Override
            public void run() {
                Integer orderId = ConsoleIO.inputInteger(ORDER, ID);
                orderController.setClosed(orderId);
            }
        });

        addItem(new ConsoleMenuItem("Add dish to open order") {
            @Override
            public void run() {
                Integer orderId = ConsoleIO.inputInteger(ORDER, ID);
                String dishName = ConsoleIO.inputString(DISH, NAME);
                orderController.addDish(orderId, dishName);
                Order order = orderController.search(orderId);
                ConsoleIO.outputItem("Changed order", order.toString());
                ConsoleIO.outputDishes(order.getDishes());
            }
        });

        addItem(new ConsoleMenuItem("Delete dish from open order") {
            @Override
            public void run() {
                Integer orderId = ConsoleIO.inputInteger(ORDER, ID);
                String dishName = ConsoleIO.inputString(DISH, NAME);
                orderController.deleteDish(orderId, dishName);
                Order order = orderController.search(orderId);
                ConsoleIO.outputItem("Changed order", order.toString());
                ConsoleIO.outputDishes(order.getDishes());
            }
        });
    }
}
