package ua.goit.timonov.enterprise.module_6_2.controllers;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.timonov.enterprise.module_6_2.model.Dish;
import ua.goit.timonov.enterprise.module_6_2.model.Order;
import ua.goit.timonov.enterprise.module_6_2.dao.OrderDAO;

import java.util.List;

/**
 * Created by Alex on 01.08.2016.
 */
public class OrderController {

    private OrderDAO orderDAO;

    @Transactional(propagation = Propagation.REQUIRED)
    public void setOrderDAO(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void add(Order order) {
        orderDAO.add(order);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(int orderId) {
        orderDAO.delete(orderId);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addDish(int orderId, Dish dish) {
        orderDAO.addDish(orderId, dish);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteDish(int orderId, Dish dish) {
        orderDAO.deleteDish(orderId, dish);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void setClosed(int orderId) {
        orderDAO.setClosed(orderId);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Order> getOpenOrders() {
        return orderDAO.getOpenOrders();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Order> getClosedOrders() {
        return orderDAO.getClosedOrders();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Order search(Integer id) {
        return orderDAO.search(id);
    }
}
