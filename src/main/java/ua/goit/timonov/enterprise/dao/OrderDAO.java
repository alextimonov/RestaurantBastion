package ua.goit.timonov.enterprise.dao;

import ua.goit.timonov.enterprise.model.Dish;
import ua.goit.timonov.enterprise.model.Employee;
import ua.goit.timonov.enterprise.model.Order;

import java.util.Date;
import java.util.List;

/**
 * DAO interface for Order
 */
public interface OrderDAO {

    /**
     * adds new order to DB
     * @param order      given order
     */
    void add(Order order);

    /**
     * searches order in DB by its ID
     * @param orderId        order's ID to find
     * @return          found order
     * throws           EmptyResultDataAccessException, DataAccessException
     */
    Order search(Integer orderId);

    /**
     * deletes order from DB by its ID
     * @param orderId           order's ID to delete
     * throws                   EmptyResultDataAccessException, DataAccessException
     */
    void delete(int orderId);

    /**
     * adds dish to order by order's ID
     * @param orderId       order's ID
     * @param dish          dish to be added
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    void addDish(int orderId, Dish dish);

    /**
     * deletes dish from order
     * @param orderId       order's ID
     * @param dish          dish to be deleted
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    void deleteDish(int orderId, Dish dish);

    /**
     * sets order to closed
     * @param orderId
     */
    void setClosed(int orderId);

    /**
     * finds list of all open orders in DB
     * @return          list of open orders
     * throws           EmptyResultDataAccessException, DataAccessException
     */
    List<Order> getOpenOrders();

    /**
     * finds list of all closed orders in DB
     * @return          list of closed orders
     * throws           EmptyResultDataAccessException, DataAccessException
     */
    List<Order> getClosedOrders();

    /**
     * finds list of all orders in DB
     * @return          list of all orders
     * throws           EmptyResultDataAccessException, DataAccessException
     */
    List<Order> getAllOrders();

    /**
     * returns orders in DB with given table number
     * @param tableNumber       given table number
     * @return                  list of orders
     */
    List<Order> getOrdersByTableNumber(int tableNumber);

    /**
     * finds orders by waiter
     * @param waiter        given waiter
     * @return              list of orders by waiter
     */
    List<Order> getOrdersByWaiter(Employee waiter);

    /**
     * finds orders by date
     * @param date          given date
     * @return              list of orders by date
     */
    List<Order> getOrdersByDate(Date date);

    /**
     * searches order by ID
     * @param id            given ID
     * @return              order by given ID
     */
    Order getOrder(int id);
}

