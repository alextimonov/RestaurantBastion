package ua.goit.timonov.enterprise.module_9.service;

import ua.goit.timonov.enterprise.module_6_2.dao.OrderDAO;
import ua.goit.timonov.enterprise.module_6_2.model.Employee;
import ua.goit.timonov.enterprise.module_6_2.model.Order;

import java.util.Date;
import java.util.List;

/**
 * Created by Alex on 17.09.2016.
 */
public class OrderService {

    private OrderDAO orderDAO;

    public void setOrderDAO(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }


    public List<Order> getAllOrders() {
        return orderDAO.getAllOrders();
    }

    public List<Order> filterByTableNumber(int tableNumber) {
        return orderDAO.getOrdersByTableNumber(tableNumber);
    }

    public List<Order> filterByWaiter(Employee waiter) {
        return orderDAO.getOrdersByWaiter(waiter);
    }

    public List<Order> filterByDate(Date date) {
        return orderDAO.getOrdersByDate(date);
    }

    public Order getOrder(int id) {
        return orderDAO.getOrder(id);
    }
}
