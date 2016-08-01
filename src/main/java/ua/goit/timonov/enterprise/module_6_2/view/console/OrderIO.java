package ua.goit.timonov.enterprise.module_6_2.view.console;

import ua.goit.timonov.enterprise.module_6_2.model.Order;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static ua.goit.timonov.enterprise.module_6_2.view.console.PrintToConsole.printEmptyLine;
import static ua.goit.timonov.enterprise.module_6_2.view.console.PrintToConsole.printLine;

/**
 * Created by Alex on 01.08.2016.
 */
public class OrderIO {

    public static void outputList(String orderType, List<Order> orders) {
        printLine("Current list of " + orderType + " orders: ");
        for (Order order : orders) {
            printLine(order.toString());
            DishIO.outputList(order.getDishes());
        }
        printEmptyLine();
    }

    public static Order inputOrder() {
        Scanner sc = new Scanner(System.in);
        printLine("Please, input date of order: ");
        Date date = Input.inputDate(sc);
        printLine("Please, input table number: ");
        int nTable = Input.inputInteger(sc);
        printLine("Please, input waiter's id: ");
        int waiterId = Input.inputInteger(sc);
        Order order = new Order(waiterId, nTable, date);
        return order;
    }

    public static void output(String explain, Order order) {
        printLine(explain);
        printLine(order.toString());
    }

    public static int inputOrderId() {
        Scanner sc = new Scanner(System.in);
        printLine("Please, input order's id: ");
        return Input.inputInteger(sc);
    }
}