package ua.goit.timonov.enterprise.module_6_2.dao.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.timonov.enterprise.module_6_2.dao.OrderDAO;
import ua.goit.timonov.enterprise.module_6_2.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * JDBC implementation of OrderDAO
 */
public class JdbcOrderDAO implements OrderDAO {

    private JdbcTemplate template;
    private JdbcDishDAO jdbcDishDAO;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.template = jdbcTemplate;
    }

    public void setJdbcDishDAO(JdbcDishDAO jdbcDishDAO) {
        this.jdbcDishDAO = jdbcDishDAO;
    }

    /**
     * adds new order to DB
     * @param order      given order
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void add(Order order) {
        String sql = "INSERT INTO orders VALUES ((SELECT max(orders.id) FROM orders) + 1, ?, ?, ?, false)";
        template.update(sql,
                order.getWaiterId(),
                order.getTableNumber(),
                order.getDate());
    }

    /**
     * searches order in DB by its ID
     * @param orderId        order's ID to find
     * @return          found order
     * throws           EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Order search(Integer orderId) {
        String sql = "SELECT * FROM orders WHERE id = ?";
        Map<String, Object> map = template.queryForMap(sql, orderId);
        return getOrderFromMap(map);
    }

    /**
     * deletes order from DB by its ID
     * @param orderId           order's ID to delete
     * throws                   EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void delete(int orderId) {
        if (orderIsOpen(orderId)) {
            deleteOrder(orderId);
        }
        else {
            throw new IllegalArgumentException("Order is not open");
        }

    }

    // returns true if order (given by its ID) is open
    @Transactional(propagation = Propagation.MANDATORY)
    private boolean orderIsOpen(int orderId) {
        String sql = "SELECT closed FROM orders WHERE id = ?";
        Map<String, Object> map = template.queryForMap(sql, orderId);
        boolean closed = (Boolean) map.get("closed");
        return !closed;
    }

    // deletes order from auxiliary table
    @Transactional(propagation = Propagation.MANDATORY)
    private void deleteOrder(int orderId) {
        String sql = "DELETE FROM dish_to_orders WHERE order_id = ?; " +
                "DELETE FROM orders WHERE id = ?;";
        template.update(sql, orderId, orderId);
    }

    /**
     * adds dish to order by order's ID
     * @param orderId       order's ID
     * @param dish          dish to be added
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void addDish(int orderId, Dish dish) {
        if (orderIsOpen(orderId)) {
            String sql = "INSERT INTO dish_to_orders VALUES (?, ?)";
            template.update(sql, orderId, dish.getId());
        }
        else {
            throw new IllegalArgumentException("Order is not open");
        }
    }

    /**
     * deletes dish from order
     * @param orderId       order's ID
     * @param dish          dish to be deleted
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void deleteDish(int orderId, Dish dish) {
        if (orderIsOpen(orderId)) {
            String sql = "DELETE FROM dish_to_orders WHERE (order_id = ? AND dish_id =?)";
            template.update(sql, orderId, dish.getId());
        }
        else {
            throw new IllegalArgumentException("Order is not open");
        }
    }

    /**
     * sets order to closed
     * @param orderId
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void setClosed(int orderId) {
        if (orderIsOpen(orderId)) {
            String sql = "UPDATE orders SET closed = 'TRUE' WHERE id = ?";
            template.update(sql, orderId);
        }
        else {
            throw new IllegalArgumentException("Order is not open");
        }
    }

    /**
     * finds list of all open orders in DB
     * @return          list of open orders
     * throws           EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Order> getOpenOrders() {
        String sql = "SELECT * FROM orders WHERE closed = 'FALSE'";
        List<Map<String, Object>> mapList = template.queryForList(sql);

        List<Order> result = new ArrayList<>();
        for (Map<String, Object> row : mapList) {
            Order order = getOrderFromMap(row);
            result.add(order);
        }
        return result;
    }

    /**
     * finds list of all closed orders in DB
     * @return          list of closed orders
     * throws           EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Order> getClosedOrders() {
        String sql = "SELECT * FROM orders WHERE closed = 'TRUE'";
        List<Map<String, Object>> mapList = template.queryForList(sql);

        List<Order> result = new ArrayList<>();
        for (Map<String, Object> row : mapList) {
            Order order = getOrderFromMap(row);
            result.add(order);
        }
        return result;
    }

    private Order getOrderFromMap(Map<String, Object> map) {
        Order order = new Order();
        order.setId((Integer) map.get("id"));
        order.setWaiterId((Integer) map.get("employee_id"));
        order.setTableNumber((Integer) map.get("table_number"));
        order.setDate((Date) map.get("date"));
        order.setClosed((Boolean) map.get("closed"));
        List<Dish> dishes = findDishesByOrderId(order.getId());
        order.setDishes(dishes);
        return order;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    private List<Dish> findDishesByOrderId(int orderId) {
        String sql = "SELECT DISH.id, DISH.name, DISH.description, DISH.cost, DISH.weight\n" +
                "FROM (DISH_TO_ORDERS INNER JOIN DISH ON DISH_TO_ORDERS.dish_id = DISH.id) \n" +
                "WHERE DISH_TO_ORDERS.order_id = ?";
        List<Map<String, Object>> mapList = template.queryForList(sql, orderId);

        List<Dish> result = new ArrayList<>();
        for (Map<String, Object> row : mapList) {
            Dish dish = jdbcDishDAO.getDishFromMap(row);
            result.add(dish);
        }
        return result;
    }
}
