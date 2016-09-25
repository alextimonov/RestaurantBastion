package ua.goit.timonov.enterprise.module_6_2.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.timonov.enterprise.module_6_2.dao.OrderDAO;
import ua.goit.timonov.enterprise.module_6_2.exceptions.ForbidToAddException;
import ua.goit.timonov.enterprise.module_6_2.exceptions.ForbidToDeleteException;
import ua.goit.timonov.enterprise.module_6_2.model.Dish;
import ua.goit.timonov.enterprise.module_6_2.model.Employee;
import ua.goit.timonov.enterprise.module_6_2.model.Order;

import java.util.Date;
import java.util.List;

/**
 * Hibernate implementation of OrderDAO
 */
public class HibernateOrderDao implements OrderDAO {

    public static final String FIELD_CLOSED = "closed";
    private SessionFactory sessionFactory;
    private JpaCriteriaQueries<Order> jpaCriteriaQueries = new JpaCriteriaQueries();

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * finds list of all open orders in DB
     * @return          list of open orders
     * throws           EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional
    public List<Order> getOpenOrders() {
        return jpaCriteriaQueries.getAllTypedOrders(sessionFactory, Order.class, false);
    }

    /**
     * finds list of all closed orders in DB
     * @return          list of closed orders
     * throws           EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional
    public List<Order> getClosedOrders() {
        return jpaCriteriaQueries.getAllTypedOrders(sessionFactory, Order.class, true);
    }

    /**
     * finds list of all orders in DB
     * @return          list of all orders
     * throws           EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional
    public List<Order> getAllOrders() {
        return jpaCriteriaQueries.getAllEntityItems(sessionFactory, Order.class);
    }

    /**
     * returns orders in DB with given table number
     * @param tableNumber       give table number
     * @return                  list of orders
     */
    @Override
    @Transactional
    public List<Order> getOrdersByTableNumber(int tableNumber) {
        return jpaCriteriaQueries.searchItemsByValue(sessionFactory, Order.class, "tableNumber", tableNumber);
    }

    @Override
    @Transactional
    public List<Order> getOrdersByWaiter(Employee waiter) {
        return jpaCriteriaQueries.searchItemsByValue(sessionFactory, Order.class, "waiter", waiter);
    }

    @Override
    @Transactional
    public List<Order> getOrdersByDate(Date date) {
        return jpaCriteriaQueries.searchItemsByValue(sessionFactory, Order.class, "date", date);
    }

    @Override
    @Transactional
    public Order getOrder(int id) {
        return sessionFactory.getCurrentSession().get(Order.class, id);
    }

    /**
     * adds new order to DB
     * @param order      given order
     */
    @Override
    @Transactional
    public void add(Order order) {
        Session session = sessionFactory.getCurrentSession();
        session.save(order);
    }

    /**
     * searches order in DB by its ID
     * @param orderId        order's ID to find
     * @return          found order
     * throws           EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional
    public Order search(Integer orderId) {
        return jpaCriteriaQueries.searchItemById(sessionFactory, Order.class, orderId);
    }

    /**
     * deletes order from DB by its ID
     * @param orderId           order's ID to delete
     * throws                   EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional
    public void delete(int orderId) {
        if (orderIsClosed(orderId)) {
            throw new IllegalArgumentException("Order is not open");
        }
        Order order = search(orderId);
        sessionFactory.getCurrentSession().delete(order);
    }

    // returns true if order (given by its ID) is open
    @Transactional
    boolean orderIsClosed(int orderId) {
        Order order = jpaCriteriaQueries.searchItemById(sessionFactory, Order.class, orderId);
        return order.getClosed();
    }

    /**
     * adds dish to order by order's ID
     * @param orderId       order's ID
     * @param dish          dish to be added
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional
    public void addDish(int orderId, Dish dish) {
        if (orderIsClosed(orderId)) {
            throw new ForbidToAddException("Order is closed");
        }
        else {
            Order order = search(orderId);
            Session session = sessionFactory.getCurrentSession();
            order.getDishes().add(dish);
            session.save(order);
        }
    }

    /**
     * deletes dish from order
     * @param orderId       order's ID
     * @param dish          dish to be deleted
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional
    public void deleteDish(int orderId, Dish dish) {
        if (orderIsClosed(orderId)) {
            throw new ForbidToDeleteException("Order is closed", "");
        }
        else {
            Session session = sessionFactory.getCurrentSession();
            Order order = search(orderId);
            if (!order.getDishes().remove(dish)) {
                throw new IllegalArgumentException("There's no dish " + dish.getName() + " in the order");
            }
            session.save(order);
        }
    }

    /**
     * sets order to closed
     * @param orderId
     */
    @Override
    @Transactional
    public void setClosed(int orderId) {
        if (orderIsClosed(orderId)) {
            throw new IllegalArgumentException("Order is not open");
        }
        else {
            jpaCriteriaQueries.updateValue(sessionFactory, Order.class, orderId, FIELD_CLOSED, true);
        }

    }
}
