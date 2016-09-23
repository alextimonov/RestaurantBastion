package ua.goit.timonov.enterprise.module_9.service;

import org.springframework.transaction.annotation.Transactional;
import ua.goit.timonov.enterprise.module_6_2.dao.OrderDAO;
import ua.goit.timonov.enterprise.module_6_2.model.Order;

import java.util.List;

/**
 * Web service for OrderDAO
 */
public class OrderService {

    private OrderDAO orderDAO;

    public void setOrderDAO(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    /**
     * finds list of all orders in DB
     * @return          list of all orders
     * throws           EmptyResultDataAccessException, DataAccessException
     */
    @Transactional
    public List<Order> getAllOrders() {
        return orderDAO.getAllOrders();
    }

    /**
     * finds list of all open orders in DB
     * @return          list of open orders
     * throws           EmptyResultDataAccessException, DataAccessException
     */
    @Transactional
    public List<Order> getOpenOrders() {
        return orderDAO.getOpenOrders();
    }

    /**
     * finds list of all closed orders in DB
     * @return          list of closed orders
     * throws           EmptyResultDataAccessException, DataAccessException
     */
    @Transactional
    public List<Order> getClosedOrders() {
        return orderDAO.getClosedOrders();
    }

    /**
     * searches order in DB by its ID
     * @param orderId   order's ID to find
     * @return          found order
     * throws           EmptyResultDataAccessException, DataAccessException
     */
    @Transactional
    public Order searhById(Integer orderId) {
        return orderDAO.search(orderId);
    }
}
