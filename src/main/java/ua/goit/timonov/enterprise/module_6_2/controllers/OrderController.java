package ua.goit.timonov.enterprise.module_6_2.controllers;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.timonov.enterprise.module_6_2.model.Dish;
import ua.goit.timonov.enterprise.module_6_2.model.Order;
import ua.goit.timonov.enterprise.module_6_2.dao.OrderDAO;

import java.util.List;

/**
 * Controller for OrderDAO
 */
public class OrderController {

    private OrderDAO orderDAO;

    public void setOrderDAO(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    /**
     * adds new order to DB
     * @param order      given order
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void add(Order order) {
        orderDAO.add(order);
    }

    /**
     * searches order in DB by its ID
     * @param orderId        order's ID to find
     * @return          found order
     * throws           EmptyResultDataAccessException, DataAccessException
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Order search(Integer orderId) {
        return orderDAO.search(orderId);
    }

    /**
     * deletes order from DB by its ID
     * @param orderId           order's ID to delete
     * throws                   EmptyResultDataAccessException, DataAccessException
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(int orderId) {
        orderDAO.delete(orderId);
    }

    /**
     * adds dish to order by order's ID
     * @param orderId       order's ID
     * @param dish          dish to be added
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addDish(int orderId, Dish dish) {
        orderDAO.addDish(orderId, dish);
    }

    /**
     * deletes dish from order
     * @param orderId       order's ID
     * @param dish          dish to be deleted
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteDish(int orderId, Dish dish) {
        orderDAO.deleteDish(orderId, dish);
    }

    /**
     * sets order to closed
     * @param orderId
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void setClosed(int orderId) {
        orderDAO.setClosed(orderId);
    }

    /**
     * finds list of all open orders in DB
     * @return          list of open orders
     * throws           EmptyResultDataAccessException, DataAccessException
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Order> getOpenOrders() {
        return orderDAO.getOpenOrders();
    }

    /**
     * finds list of all closed orders in DB
     * @return          list of closed orders
     * throws           EmptyResultDataAccessException, DataAccessException
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Order> getClosedOrders() {
        return orderDAO.getClosedOrders();
    }
}
