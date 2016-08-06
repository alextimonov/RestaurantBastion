package ua.goit.timonov.enterprise.module_6_2.view.menus;

import ua.goit.timonov.enterprise.module_6_2.controllers.DishController;
import ua.goit.timonov.enterprise.module_6_2.controllers.OrderController;
import ua.goit.timonov.enterprise.module_6_2.model.Dish;
import ua.goit.timonov.enterprise.module_6_2.model.Order;
import ua.goit.timonov.enterprise.module_6_2.view.console.ConsoleIO;

import java.util.List;

import static ua.goit.timonov.enterprise.module_6_2.view.console.ConsolePrinter.printLine;

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
    public static final String NO_SUCCESS = "UNSUCCESSFUL! There's no order with such ";

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
                try {
                    List<Order> openOrders = orderController.getOpenOrders();
                    ConsoleIO.outputOrders(OPEN, openOrders);
                } catch (RuntimeException e) {
                    printLine("UNSUCCESSFUL! There's no open orders in the base!");
                }
            }
        });

        addItem(new ConsoleMenuItem("Get all closed orders") {
            @Override
            public void run() {
                try {
                    List<Order> closedOrders = orderController.getClosedOrders();
                    ConsoleIO.outputOrders(CLOSED, closedOrders);
                } catch (RuntimeException e) {
                    printLine("UNSUCCESSFUL! There's no closed orders in the base!");
                }
            }
        });

        addItem(new ConsoleMenuItem("Add new open order") {
            @Override
            public void run() {
                Order newOrder = ConsoleIO.inputOrder();
                ConsoleIO.outputItem("Order to add: ", newOrder.toString());
                try {
                    orderController.add(newOrder);
                    printLine(SUCCESS);
                } catch (Exception e) {
                    ConsoleIO.outputItem(e.getMessage(), String.valueOf(newOrder.getId()));
                }
            }
        });

        addItem(new ConsoleMenuItem("Delete open order") {
            @Override
            public void run() {
                int orderId = ConsoleIO.inputInteger(ORDER, ID);
                try {
                    orderController.delete(orderId);
                    printLine(SUCCESS);
                } catch (Exception e) {
                    ConsoleIO.outputItem(NO_SUCCESS + ID, String.valueOf(orderId));
                }
            }
        });

        addItem(new ConsoleMenuItem("Set open order to closed") {
            @Override
            public void run() {
                Integer orderId = ConsoleIO.inputInteger(ORDER, ID);
                try {
                    orderController.setClosed(orderId);
                    printLine(SUCCESS);
                } catch (Exception e) {
                    ConsoleIO.outputItem("UNSUCCESSFUL! Unable to set to closed order with " + ID, String.valueOf(orderId));
                }
            }
        });

        addItem(new ConsoleMenuItem("Add dish to open order") {
            @Override
            public void run() {
                Integer orderId = ConsoleIO.inputInteger(ORDER, ID);
                String dishName = ConsoleIO.inputString(DISH, NAME);
                try {
                    Dish dish = dishController.search(dishName);
                    orderController.addDish(orderId, dish);
                    Order order = orderController.search(orderId);
                    ConsoleIO.outputItem(SUCCESS + " Changed order", order.toString());
                    ConsoleIO.outputDishes(order.getDishes());
                } catch (RuntimeException e) {
                    printLine("UNSUCCESSFUL! Dish was not added to order");
                }
            }
        });

        addItem(new ConsoleMenuItem("Delete dish from open order") {
            @Override
            public void run() {
                Integer orderId = ConsoleIO.inputInteger(ORDER, ID);
                String dishName = ConsoleIO.inputString(DISH, NAME);

                try {
                    Dish dish = dishController.search(dishName);
                    orderController.deleteDish(orderId, dish);
                    Order order = orderController.search(orderId);
                    ConsoleIO.outputItem(SUCCESS + " Changed order:", order.toString());
                    ConsoleIO.outputDishes(order.getDishes());
                } catch (RuntimeException e) {
                    printLine("UNSUCCESSFUL! Dish was not deleted from order");
                }
            }
        });
    }
}
