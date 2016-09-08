package ua.goit.timonov.enterprise.module_9.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.goit.timonov.enterprise.module_6_2.model.Order;
import ua.goit.timonov.enterprise.module_9.service.OrderService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 07.09.2016.
 */
@RestController
public class OrderWebController {

    private OrderService orderService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/restOrders", method = RequestMethod.GET)
    public List<Order> restOrders() {
        List<Order> ordersFromDB = orderService.getAllOrders();
        return getOrdersWithoutRecursion(ordersFromDB);
    }

    private List<Order> getOrdersWithoutRecursion(List<Order> ordersFromDB) {
        List<Order> resultOrders = new ArrayList<>();
        for (Order orderFromDB : ordersFromDB) {
            Order order = getOrderWithoutRecursion(orderFromDB);
            resultOrders.add(order);
        }
        return resultOrders;
    }

    private Order getOrderWithoutRecursion(Order orderFromDB) {
        Order order = new Order();
        order.setId(orderFromDB.getId());
        order.setTableNumber(orderFromDB.getTableNumber());
        order.setDate(orderFromDB.getDate());
        order.setClosed(orderFromDB.getClosed());
        order.setDishes(orderFromDB.getDishes());
        order.setWaiter(orderFromDB.getEmployee());
        return order;
    }

    @RequestMapping(value = "/restOrders/open", method = RequestMethod.GET)
    public List<Order> restOpenOrders() {
        List<Order> ordersFromDB = orderService.getOpenOrders();
        return getOrdersWithoutRecursion(ordersFromDB);
    }

    @RequestMapping(value = "/restOrders/closed", method = RequestMethod.GET)
    public List<Order> restClosedOrders() {
        List<Order> ordersFromDB =  orderService.getClosedOrders();
        return getOrdersWithoutRecursion(ordersFromDB);
    }

    @RequestMapping(value = "/restOrders/{orderId}", method = RequestMethod.GET)
    public Order getOrderById(@PathVariable Integer orderId) {
        Order orderFromDB = orderService.searhById(orderId);
        Order order = getOrderWithoutRecursion(orderFromDB);
        return order;
    }
}
